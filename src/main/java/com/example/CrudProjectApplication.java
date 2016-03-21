package com.example;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.example.model.Client;


@SpringBootApplication
public class CrudProjectApplication{
	private RestTemplate restTemplate;
	
	public CrudProjectApplication() {
		restTemplate = new RestTemplate();
	}

	private void delete(long phoneNumber){
		String url = "http://localhost:8080/delete/{phoneNumber}";
		Map<String, Long> params = new HashMap<>();
		params.put("phoneNumber", phoneNumber);
		restTemplate.delete(url, params);
	}
	
	private void create(Client client){
		String url = "http://localhost:8080/save";
		Map<String, String> params = new HashMap<>();
		params.put("login", client.getLogin());
		params.put("password", client.getPassword());
		params.put("phoneNumber", client.getPhoneNumber().toString());
		restTemplate.put(url, params);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CrudProjectApplication.class, args);
		CrudProjectApplication crud = new CrudProjectApplication();
		Client client = new Client();
		client.setLogin("first");
		client.setPassword("passwordFirst");
		client.setPhoneNumber(3536637L);
		crud.create(client);
	}
	
	
}
