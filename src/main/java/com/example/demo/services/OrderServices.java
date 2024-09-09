package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class OrderServices {

    private OrderRepository oRepository;

    public List<Order> findAll() {
        return oRepository.findAll();
    }

    public Order findById(String id) {
        Optional<Order> orderObj = oRepository.findById(id);
        return orderObj.orElseThrow(() -> new ObjectNotFoundException("Pedido não encontrado"));
    }

}
