package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Item;
import com.example.demo.dto.ItemDTO;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class ItemServices {

    @Autowired
    private ItemRepository iRepository;

    @Autowired
    private MenuRepository mRepository;

    @Autowired
    private RestaurantRepository rRepository;

    public List<Item> findAll() {
        return iRepository.findAll();
    }

    public Item findById(String id) {
        Optional<Item> item = iRepository.findById(id);
        return item.orElseThrow(() -> new ObjectNotFoundException("Item não encontrado"));
    }

    public Item insert(Item itemObj) {
        return iRepository.insert(itemObj);
    }

    public void delete(String id) {
        findById(id);
        iRepository.deleteById(id);
    }

    public List<Item> fullSearch(String name, Double minValue, double maxValue) {
        return iRepository.fullSearch(name, minValue, maxValue);
    }

    public Item update(Item newItemObj) {
        Item itemObj = findById(newItemObj.getId());
        updateData(itemObj, newItemObj);
        return iRepository.save(itemObj);
    }

    private void updateData(Item itemObj, Item newItemObj) {
        itemObj.setName(newItemObj.getName() != null ? newItemObj.getName() : itemObj.getName());
        itemObj.setValue(newItemObj.getValue() != null ? newItemObj.getValue() : itemObj.getValue());
        itemObj.setDescription(newItemObj.getDescription() != null ? newItemObj.getDescription() : itemObj.getDescription());
        itemObj.setMenu(newItemObj.getMenu() != null ? newItemObj.getMenu() : itemObj.getMenu());
    }

    public void createItems(Item item) {
        iRepository.save(item);
        mRepository.save(item.getMenu());
        rRepository.save(item.getMenu().getFromRestaurant());
    }

    public void removeAll() {
        iRepository.deleteAll();
    }

    public Item fromDTO(ItemDTO item) {
        return new Item(item.getId(), item.getName(), item.getValue(), 
        item.getDescription(), item.getMenuReference());
    }
}
