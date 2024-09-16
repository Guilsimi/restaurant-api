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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Item;
import com.example.demo.domain.Menu;
import com.example.demo.dto.ItemDTO;
import com.example.demo.resources.utils.URL;
import com.example.demo.services.ItemServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/item")
public class ItemResources {

    @Autowired
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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ItemDTO objDTO) {
        Item itemObj = iServices.fromDTO(objDTO);
        itemObj = iServices.insert(itemObj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(itemObj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        iServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ItemDTO objDTO, @PathVariable String id) {
        Item itemObj = iServices.fromDTO(objDTO);
        itemObj.setId(id);
        itemObj = iServices.update(itemObj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/fullsearch")
    public ResponseEntity<List<Item>> fullSearch(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "minValue", defaultValue = "") String minValue,
            @RequestParam(value = "maxValue", defaultValue = "100000") String maxValue) {
        name = URL.decodeParam(name);
        List<Item> list = iServices.fullSearch(name, URL.convertDouble(minValue), URL.convertDouble(maxValue));
        return ResponseEntity.ok().body(list);
    }

    public void createItems(Item item) {
        for (Menu m : Menu.getMenuList()) {
            if (item.getMenu().equals(m)) {
                m.getItems().add(item);
            }
        }
        iServices.createItems(item);
    }

    public void removeAll() {
        iServices.removeAll();
    }
}
