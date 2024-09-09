package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Restaurant;

public class FromRestaurantDTO implements Serializable {

    private String id;
    private String name;

    public FromRestaurantDTO() {

    }

    public FromRestaurantDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
