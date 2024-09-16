package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Client;
import com.example.demo.domain.Order;
import com.example.demo.domain.enums.PaymentForm;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class OrderDTO implements Serializable {

    private String id;
    private PaymentForm payment;
    private String address;
    private Client client;
    private Double total;

    public OrderDTO() {

    }

    public OrderDTO(Order order) {
        id = order.getId();
        payment = order.getPaymentForm();
        address = order.getAddress();
        total = order.getTotal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
