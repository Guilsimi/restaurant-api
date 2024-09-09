package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Item;

public class ItemDTO implements Serializable {

    private String id;
    private String name;
    private Double value;
    private String description;

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        id = item.getId();
        name = item.getName();
        value = item.getValue();
        description = item.getDescription();
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

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
