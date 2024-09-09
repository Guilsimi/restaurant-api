package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Menu;

public class MenuRefDTO implements Serializable {

    private String id;
    private String type;

    public MenuRefDTO() {
    }

    public MenuRefDTO(Menu menu) {
        this.id = menu.getId();
        this.type = menu.getType();
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

}
