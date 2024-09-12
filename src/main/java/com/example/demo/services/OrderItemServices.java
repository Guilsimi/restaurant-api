package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.OrderItem;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class OrderItemServices {

    @Autowired
    private OrderItemRepository oiRepository;

    @Autowired
    private OrderRepository oRepository;

    @Autowired 
    private ClientRepository cRepository;

    public List<OrderItem> findAll() {
        return oiRepository.findAll();
    }

    public OrderItem findById(String id) {
        Optional<OrderItem> orderItem = oiRepository.findById(id);
        return orderItem.orElseThrow(() -> new ObjectNotFoundException("Item do pedido não encontrado"));
    }

    public void removeAll() {
        oiRepository.deleteAll();
    }

    public void createOrderItems(OrderItem orderItem) {
        oiRepository.save(orderItem);
        oRepository.save(orderItem.getOrder());
        cRepository.save(orderItem.getOrder().getOrderClient());
    }

}
