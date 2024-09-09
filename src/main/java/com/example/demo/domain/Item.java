package com.example.demo.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.dto.MenuRefDTO;

@Document
public class Item implements Serializable {
    
    @Id
    private String id;
    private String name;
    private Double value;
    private String description;
    private MenuRefDTO menuReference;
    
    public Item() {
    }

    public Item(String id, String name, Double value, String description, MenuRefDTO menuReference) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.description = description;
        this.menuReference = menuReference;
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

    public MenuRefDTO getMenu() {
        return menuReference;
    }

    public void setMenu(MenuRefDTO menuReference) {
        this.menuReference = menuReference;
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
        Item other = (Item) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
