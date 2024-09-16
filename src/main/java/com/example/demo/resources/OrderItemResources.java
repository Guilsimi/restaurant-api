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

import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.dto.OrderItemDTO;
import com.example.demo.services.OrderItemServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/order-item")
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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody OrderItemDTO objDTO) {
        OrderItem oiObj = oiServices.fromDTO(objDTO);
        oiObj = oiServices.insert(oiObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(oiObj.getItem().getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        oiServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    
    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody OrderItemDTO objDto, @PathVariable String id) {
        OrderItem oiObj = oiServices.fromDTO(objDto);
        oiObj = oiServices.update(oiObj);
        return ResponseEntity.noContent().build();
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
