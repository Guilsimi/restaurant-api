package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Restaurant;
import com.example.demo.repository.RestaurantRepository;

@Service
public class RestaurantServices {

    @Autowired
    private RestaurantRepository restRepository;

    public List<Restaurant> findAll() {
        return restRepository.findAll();
    }
}
