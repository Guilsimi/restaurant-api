package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Restaurant;
import com.example.demo.dto.RestaurantsDTO;
import com.example.demo.services.RestaurantServices;

import jakarta.annotation.Resource;

@Resource
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

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<RestaurantsDTO> findByEmail(@PathVariable String email) {
        Restaurant restaurant = restService.findByEmail(email);
        return ResponseEntity.ok().body(new RestaurantsDTO(restaurant));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody RestaurantsDTO objDTO) {
        Restaurant restObj = restService.fromDTO(objDTO);
        restObj = restService.insert(restObj, restObj.getPassword());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        restService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody RestaurantsDTO objDto, @PathVariable String id) {
        Restaurant restObj = restService.fromDTO(objDto);
        restObj.setId(id);
        restObj = restService.update(restObj);
        return ResponseEntity.noContent().build();
    }

    public void createRestaurant(Restaurant restaurant) {
        restService.createRestaurant(restaurant, restaurant.getPassword());
    }

    public void removeAll() {
        restService.removeAll();
     }
}
