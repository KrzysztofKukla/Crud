package com.example.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Client;

public interface ClientDao extends CrudRepository<Client, Long> {
	Client getByPhoneNumber(long phoneNumber);
}
