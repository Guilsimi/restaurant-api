package com.example.demo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.domain.enums.PaymentForm;
import com.example.demo.services.exception.ObjectNotFoundException;

@Document
public class Order implements Serializable {

    private static List<Order> ordersList = new ArrayList<>();
    @Id
    private String id;
    private int payment;
    private String address;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Double total;

    @DBRef
    private Client orderClient;
    

    public Order() {

    }

    public Order(String id, PaymentForm payment, String address, Client orderClient) {
        this.id = id;
        setPaymentForm(payment);
        this.address = address;
        this.orderClient = orderClient;

        ordersList.add(this);
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

    public PaymentForm getPaymentForm() {
        return PaymentForm.valueOf(payment);
    }

    public void setPaymentForm(PaymentForm paymentForm) {
        if (paymentForm != null) {
            this.payment = paymentForm.getCode();
        }
    }

    public Client getOrderClient() {
        return orderClient;
    }

    public void setOrderClient(Client orderClient) {
        this.orderClient = orderClient;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    private void updateTotal() {
        double newTotal = 0.0;
        for (OrderItem item : orderItems) {
            newTotal += item.calculateSubTotal();
        }
        this.total = newTotal;
    }

    public void addOrderItem(OrderItem items) {
        this.orderItems.add(items);
        updateTotal();
    }

    public void removeOrderItem(OrderItem item) {
        if (getOrderItems().contains(item)) {
            this.orderItems.remove(item);
            updateTotal();
        } else {
            throw new ObjectNotFoundException("O pedido não contém o objeto");
        }
    }

    public Double getTotal() {
        return total;
    }

    public static List<Order> getOrdersList() {
        return ordersList;
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
