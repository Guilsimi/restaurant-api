package com.example.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Restaurant;
import com.example.demo.dto.RestaurantsDTO;
import com.example.demo.services.RestaurantServices;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantResources {

    @Autowired
    private RestaurantServices restService;

    @GetMapping
    public ResponseEntity<List<RestaurantsDTO>> findAll() {
        List<Restaurant> restList = restService.findAll();
        List<RestaurantsDTO> dtoRestList = restList.stream().map(obj -> new RestaurantsDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoRestList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestaurantsDTO> findById(@PathVariable String id) {
        Restaurant restaurant = restService.findById(id);
        return ResponseEntity.ok().body(new RestaurantsDTO(restaurant));
    }

}
