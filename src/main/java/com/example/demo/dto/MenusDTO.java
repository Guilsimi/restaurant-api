package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Menu;
import com.example.demo.domain.Restaurant;

public class MenusDTO implements Serializable {

    private String id;
    private String type;
    private Restaurant fromRestaurant;
    
    public MenusDTO() {
    }

    public MenusDTO(Menu menu) {
        id = menu.getId();
        type = menu.getType();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Restaurant getFromRestaurant() {
        return fromRestaurant;
    }

    public void setFromRestaurant(Restaurant fromRestaurant) {
        this.fromRestaurant = fromRestaurant;
    }

    

}
