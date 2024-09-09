package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Client;

public class OrderClientDTO implements Serializable {

    private String id;
    private String name;

    public OrderClientDTO() {

    }

    public OrderClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
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
