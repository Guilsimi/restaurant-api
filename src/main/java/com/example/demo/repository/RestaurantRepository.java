package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Restaurant;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, String> {

        Optional<Restaurant> findByEmail(String email);

}
