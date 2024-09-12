package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Item;
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

    public void createItems(Item item) {
        iRepository.save(item);
        mRepository.save(item.getMenu());
        rRepository.save(item.getMenu().getFromRestaurant());
    }

    public void removeAll() {
        iRepository.deleteAll();
    }
}
