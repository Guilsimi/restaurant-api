package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.example.demo.domain.Restaurant;
import com.example.demo.repository.RestaurantRepository;

public class Instantiation implements CommandLineRunner {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public void run(String... args) throws Exception {

        restaurantRepository.deleteAll();

        Restaurant marmitaria = new Restaurant(null, "MarmitOns", 999999999L, 
        00700700001007L, "marmitons@gmail.com", "senhadamarmitaria");

        restaurantRepository.saveAll(Arrays.asList(marmitaria));
    }

}
