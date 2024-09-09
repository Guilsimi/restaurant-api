package com.example.demo.domain;

import java.io.Serializable;

import com.example.demo.domain.pk.OrderItemPK;

public class OrderItem implements Serializable {

    private OrderItemPK id;
    private Integer quantity;

    public OrderItem() {

    }

    public OrderItem(Order order, Item item, Integer quantity) {
        id.setOrder(order);
        id.setItem(item);
        this.quantity = quantity;
    }

    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Item getItem() {
        return id.getItem();
    }

    public void setItem(Item item) {
        id.setItem(item);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
