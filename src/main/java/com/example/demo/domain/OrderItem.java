package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class OrderItem implements Serializable {

    private static List<OrderItem> orderItemList = new ArrayList<>();

    private String id;
    private Item item;
    private Integer quantity;
    private Double subTotal;

    @DBRef(lazy = true)
    private Order order;

    public OrderItem() {

    }

    public OrderItem(Item item, Order order, Integer quantity) {
        this.id = item.getId();
        this.item = item;
        this.quantity = quantity;
        this.order = order;

        orderItemList.add(this);
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public static List<OrderItem> getOrderItemList() {
        return orderItemList;
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
