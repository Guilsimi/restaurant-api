package com.example.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.services.OrderItemServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/order_item")
public class OrderItemResources {

    @Autowired
    private OrderItemServices oiServices;

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> findAll() {
        List<OrderItem> iList = oiServices.findAll();
        List<OrderItemDTO> iDtoList = iList.stream().map(item -> new OrderItemDTO(item)).collect(Collectors.toList());
        return ResponseEntity.ok().body(iDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderItemDTO> findById(@PathVariable String id) {
        OrderItem iObj = oiServices.findById(id);
        return ResponseEntity.ok().body(new OrderItemDTO(iObj));
    }

    public void removeAll() {
       oiServices.removeAll();
    }

    public void createOrderItems(OrderItem orderItem) {
        for(Order o : Order.getOrdersList()) {
            if(orderItem.getOrder().equals(o)) {
                o.addOrderItem(orderItem);
            }
        }
        oiServices.createOrderItems(orderItem);
    }
    
}
