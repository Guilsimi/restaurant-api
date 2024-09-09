package com.example.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Order;
import com.example.demo.dto.OrdersDTO;
import com.example.demo.services.OrderServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/order")
public class OrderResources {

    private OrderServices oServices;

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> findAll() {
        List<Order> oList = oServices.findAll();
        List<OrdersDTO> oDtoList = oList.stream().map(obj -> new OrdersDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(oDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrdersDTO> findById(@PathVariable String id) {
        Order orderObj = oServices.findById(id);
        return ResponseEntity.ok().body(new OrdersDTO(orderObj));
    }

}
