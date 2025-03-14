package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Client;
import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDTO;
import com.example.demo.services.OrderServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/order")
public class OrderResources {

    @Autowired
    private OrderServices oServices;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<Order> oList = oServices.findAll();
        List<OrderDTO> oDtoList = oList.stream().map(obj -> new OrderDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(oDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable String id) {
        Order orderObj = oServices.findById(id);
        return ResponseEntity.ok().body(new OrderDTO(orderObj));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody OrderDTO objDTO) {
        Order orderObj = oServices.fromDTO(objDTO);
        orderObj = oServices.insert(orderObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderObj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        oServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody OrderDTO objDto, @PathVariable String id) {
        Order orderObj = oServices.fromDTO(objDto);
        orderObj.setId(id);
        orderObj = oServices.update(orderObj);
        return ResponseEntity.noContent().build();
    }

    public void createOrders(Order order) {
        for (Client c : Client.getClientList()) {
            if (order.getOrderClient().equals(c)) {
                c.getOrders().add(order);
            }
        }
        oServices.createOrders(order);
    }

    public void removeAll() {
        oServices.removeAll();
    }
}
