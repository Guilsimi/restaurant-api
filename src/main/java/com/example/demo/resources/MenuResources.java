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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody MenuDTO objDTO) {
        Menu menuObj = mServices.fromDTO(objDTO);
        menuObj = mServices.insert(menuObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(menuObj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        mServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody MenuDTO objDTO, @PathVariable String id) {
        Menu menuObj = mServices.fromDTO(objDTO);
        menuObj.setId(id);
        menuObj = mServices.update(menuObj);
        return ResponseEntity.noContent().build();
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
