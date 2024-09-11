package com.example.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Menu;
import com.example.demo.dto.MenusDTO;
import com.example.demo.services.MenuServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/menu")
public class MenuResources {

    @Autowired
    private MenuServices mServices;

    @GetMapping
    public ResponseEntity<List<MenusDTO>> findAll() {
        List<Menu> mList = mServices.findAll();
        List<MenusDTO> mDtoList = mList.stream().map(obj -> new MenusDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(mDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MenusDTO> findById(@PathVariable String id) {
        Menu menuObj = mServices.findById(id);
        return ResponseEntity.ok().body(new MenusDTO(menuObj));
    }
}
