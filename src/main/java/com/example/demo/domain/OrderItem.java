package com.example.demo.domain;

import java.io.Serializable;

public class OrderItem implements Serializable {

    private String id;
    private Item item;
    private Integer quantity;
    private Double subTotal;

    public OrderItem() {

    }

    public OrderItem(Item item, Integer quantity) {
        this.id = item.getId();
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double calculateSubTotal() {
        Double subTotal = getItem().getValue() * quantity;
        this.subTotal = subTotal;
        return subTotal;
    }

    public Double getSubTotal() {
        return subTotal;
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
        OrderItem other = (OrderItem) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
