package com.example.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Item;
import com.example.demo.dto.ItemDTO;
import com.example.demo.services.ItemServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/item")
public class ItemResources {

    private ItemServices iServices;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> findAll() {
        List<Item> iList = iServices.findAll();
        List<ItemDTO> iDtoList = iList.stream().map(item -> new ItemDTO(item)).collect(Collectors.toList());
        return ResponseEntity.ok().body(iDtoList);
    }

    @GetMapping(value = "/{id}") 
    public ResponseEntity<ItemDTO> findById(@PathVariable String id) {
        Item iObj = iServices.findById(id);
        return ResponseEntity.ok().body(new ItemDTO(iObj));
    }
}
