package com.example.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ClientDao;
import com.example.model.Client;

@RestController
public class CrudController {
	
	private final static Logger logger = LoggerFactory.getLogger(CrudController.class);
	
	@Autowired
	private ClientDao clientDao;
	
    @RequestMapping(value="/get/{phoneNumber}", method = RequestMethod.GET, produces = "application/json")
	public Client get(@PathVariable long phoneNumber ){
		Client client = clientDao.getByPhoneNumber(phoneNumber);
		if (client!=null){
			logger.info("Client exist");
			return client;
		}
		else{
			logger.error("Client doesn't exist");
			throw new RuntimeException();
		}
	}
	
	@RequestMapping(value="/delete/{phoneNumber}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable long phoneNumber){
		Client client = clientDao.getByPhoneNumber(phoneNumber);
		if (client!=null){
			clientDao.delete(client);
			logger.info("Client has been deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else{
			logger.error("Client doesn't exist");
			throw new RuntimeException();
		}
	}
	
	@RequestMapping(value="/save", method = RequestMethod.PUT)
	public ResponseEntity<Client> save(@RequestBody Client client){
		if (clientDao.getByPhoneNumber(client.getPhoneNumber())==null){
			clientDao.save(client);
			logger.info("Client has been saved");
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		else{
			logger.error("Client with that phoneNumber exists");
			return new ResponseEntity<>(HttpStatus.FOUND);
		}
	}
	
	
}
