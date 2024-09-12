package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Item;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;

public class OrderItemDTO implements Serializable {

    private String id;
    private Item item;
    private Integer quantity;
    private Double subTotal;
    private Order order;

    public OrderItemDTO() {

    }

    public OrderItemDTO(OrderItem orderItem) {
        id = orderItem.getItem().getId();
        item = orderItem.getItem();
        quantity = orderItem.getQuantity();
        order = orderItem.getOrder();
        subTotal = orderItem.getSubTotal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    
}
