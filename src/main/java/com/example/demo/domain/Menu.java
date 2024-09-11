package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.FromRestaurantDTO;

@Document
public class Menu implements Serializable {

    private static List<Menu> menuList = new ArrayList<>();

    @Id
    private String id;
    private String type;
    private FromRestaurantDTO fromRestaurant;
    
    private List<Item> items = new ArrayList<>();

    public Menu() {
    }

    public Menu(String id, String type, FromRestaurantDTO fromRestaurant) {
        this.id = id;
        this.type = type;
        this.fromRestaurant = fromRestaurant;

        menuList.add(this);
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

    public List<Item> getItems() {
        return items;
    }

    public static List<Menu> getMenuList() {
        return menuList;
    } 

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public FromRestaurantDTO getFromRestaurant() {
        return fromRestaurant;
    }

    public void setFromRestaurant(FromRestaurantDTO fromRestaurant) {
        this.fromRestaurant = fromRestaurant;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Menu other = (Menu) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
