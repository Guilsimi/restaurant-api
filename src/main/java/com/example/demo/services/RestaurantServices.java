package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Restaurant;
import com.example.demo.dto.RestaurantsDTO;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class RestaurantServices {

    @Autowired
    private RestaurantRepository restRepository;

    public List<Restaurant> findAll() {
        return restRepository.findAll();
    }

    public Restaurant findById(String id) {
        Optional<Restaurant> restaurant = restRepository.findById(id);
        return restaurant.orElseThrow(() -> new ObjectNotFoundException("Restaurante não encontrado"));
    }

    public Restaurant insert(Restaurant restObj) {
        return restRepository.insert(restObj);
    }

    public void delete(String id) {
        findById(id);
        restRepository.deleteById(id);
    }

    public Restaurant fromDTO(RestaurantsDTO objDto) {
        return new Restaurant(objDto.getId(), objDto.getName(), objDto.getPhone(),
        objDto.getCnpj(), objDto.getEmail(), objDto.getPassword());
    }
}
