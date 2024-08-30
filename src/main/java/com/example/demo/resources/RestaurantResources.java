package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Restaurant;
import com.example.demo.services.RestaurantServices;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantResources {

    @Autowired
    private RestaurantServices restService;

    @GetMapping
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> restList = restService.findAll();
        return ResponseEntity.ok().body(restList);
    }

}
