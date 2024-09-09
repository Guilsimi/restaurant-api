package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Client;

public class ClientsDTO implements Serializable {

    private String id;
    private String name;
    private Long phone;
    private String email;
    private String password;

    public ClientsDTO() {

    }

    public ClientsDTO(Client cObj) {
        id = cObj.getId();
        name = cObj.getName();
        phone = cObj.getPhone();
        email = cObj.getEmail();
        password = cObj.getPassword();
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

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
