package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.utils.PasswordUtil;

@Document
public class Restaurant implements Serializable {

    private static List<Restaurant> restaurantsList = new ArrayList<>();

    @Id
    private String id;
    private String name;
    private Long phone;
    private Long cnpj;
    private String email;
    private String password;
    
    private List<Menu> menus = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String id, String name, Long phone, Long cnpj, String email, String password) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;

        restaurantsList.add(this);
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
        this.password = PasswordUtil.hashPassword(password);
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public static List<Restaurant> getRestaurantsList() {
        return restaurantsList;
    }  

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Restaurant other = (Restaurant) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
