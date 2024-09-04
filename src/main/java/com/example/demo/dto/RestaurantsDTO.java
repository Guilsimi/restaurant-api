package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Restaurant;

public class RestaurantsDTO implements Serializable {

    private String id;
    private String name;
    private Long phone;
    private Long cnpj;
    private String email;
    private String password;

    public RestaurantsDTO() {

    }

    public RestaurantsDTO(Restaurant rObj) {
        id = rObj.getId();
        name = rObj.getName();
        phone = rObj.getPhone();
        cnpj = rObj.getCnpj();
        email = rObj.getEmail();
        password = rObj.getPassword();
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

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
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
