package com.example.demo.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.domain.enums.PaymentForm;
import com.example.demo.dto.OrderClientDTO;

@Document
public class Order implements Serializable {

    @Id
    private String id;
    private Double value;
    private int payment;
    private String address;
    private OrderClientDTO orderClient;
    
    public Order() {

    }

    public Order(String id, Double value, PaymentForm payment, String address, OrderClientDTO orderClient) {
        this.id = id;
        this.value = value;
        setPaymentForm(payment);
        this.address = address;
        this.orderClient = orderClient;
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

    public PaymentForm getPaymentForm() {
        return PaymentForm.valueOf(payment);
    }

    public void setPaymentForm(PaymentForm paymentForm) {
        if(paymentForm != null) {
            this.payment = paymentForm.getCode();
        }
    }

    public OrderClientDTO getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(OrderClientDTO orderClient) {
        this.orderClient = orderClient;
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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
    
}
