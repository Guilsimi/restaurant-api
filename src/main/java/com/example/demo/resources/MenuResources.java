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
import com.example.demo.domain.Restaurant;
import com.example.demo.dto.MenuDTO;
import com.example.demo.services.MenuServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/menu")
public class MenuResources {

    @Autowired
    private MenuServices mServices;

    @GetMapping
    public ResponseEntity<List<MenuDTO>> findAll() {
        List<Menu> mList = mServices.findAll();
        List<MenuDTO> mDtoList = mList.stream().map(obj -> new MenuDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(mDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MenuDTO> findById(@PathVariable String id) {
        Menu menuObj = mServices.findById(id);
        return ResponseEntity.ok().body(new MenuDTO(menuObj));
    }

    public void createMenu(Menu menu) {
        for (Restaurant r : Restaurant.getRestaurantsList()) {
            if (menu.getFromRestaurant().equals(r)) {
                r.getMenus().add(menu);
            }
        }
        mServices.createMenu(menu);
    }

    public void removeAll() {
        mServices.removeAll();
     }
}
