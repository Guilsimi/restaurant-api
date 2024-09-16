package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.OrderItem;
import com.example.demo.dto.OrderItemDTO;
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

     public OrderItem insert(OrderItem orderItemObj) {
        return oiRepository.insert(orderItemObj);
    }

    public void delete(String id) {
        findById(id);
        oiRepository.deleteById(id);
    }

    public void removeAll() {
        oiRepository.deleteAll();
    }

    public OrderItem update(OrderItem orderItemObj) {
        OrderItem newOrderItemObj = findById(orderItemObj.getItem().getId());
        updateData(newOrderItemObj, orderItemObj);
        return oiRepository.save(newOrderItemObj);
    }

    public void updateData(OrderItem newOrderItem, OrderItem orderItem) {
        newOrderItem.setItem(orderItem.getItem() != null ? orderItem.getItem() : newOrderItem.getItem());
        newOrderItem.setOrder(orderItem.getOrder() != null ? orderItem.getOrder() : newOrderItem.getOrder());
        newOrderItem.setQuantity(orderItem.getQuantity() != null ? orderItem.getQuantity() : newOrderItem.getQuantity());
    }

    public void createOrderItems(OrderItem orderItem) {
        oiRepository.save(orderItem);
        oRepository.save(orderItem.getOrder());
        cRepository.save(orderItem.getOrder().getOrderClient());
    }

    public OrderItem fromDTO(OrderItemDTO objDTO) {
        return new OrderItem(objDTO.getItem(), objDTO.getOrder(), objDTO.getQuantity());
    }
}
