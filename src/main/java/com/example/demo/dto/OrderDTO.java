package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Client;
import com.example.demo.domain.Order;
import com.example.demo.domain.enums.PaymentForm;

public class OrderDTO implements Serializable {

    private String id;
    private Double value;
    private PaymentForm payment;
    private String address;
    private Client client;
    
    public OrderDTO() {

    }

    public OrderDTO(Order order) {
        id = order.getId();
        payment = order.getPaymentForm();
        address = order.getAddress();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PaymentForm getPayment() {
        return payment;
    }

    public void setPayment(PaymentForm payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    
}
