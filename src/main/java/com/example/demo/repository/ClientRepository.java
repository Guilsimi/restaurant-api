package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Client;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

}
