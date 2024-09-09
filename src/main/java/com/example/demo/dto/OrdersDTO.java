package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Order;
import com.example.demo.domain.enums.PaymentForm;

public class OrdersDTO implements Serializable {

    private String id;
    private Double value;
    private PaymentForm payment;
    private String address;
    
    public OrdersDTO() {

    }

    public OrdersDTO(Order order) {
        id = order.getId();
        value = order.getValue();
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

}
