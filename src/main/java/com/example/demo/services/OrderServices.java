package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class OrderServices {

    @Autowired
    private OrderRepository oRepository;

    @Autowired
    ClientRepository cRepository;

    public List<Order> findAll() {
        return oRepository.findAll();
    }

    public Order findById(String id) {
        Optional<Order> orderObj = oRepository.findById(id);
        return orderObj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public void createOrders(Order order) {
        oRepository.save(order);
        cRepository.save(order.getOrderClient());
    }

    public void removeAll() {
        oRepository.deleteAll();
    }

    public Order fromDTO(OrderDTO orderDTO) {
        return new Order(orderDTO.getId(), orderDTO.getPayment(), orderDTO.getAddress(), orderDTO.getClient());
    }

}
