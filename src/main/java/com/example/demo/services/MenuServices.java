package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Menu;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class MenuServices {

    @Autowired
    private MenuRepository mRepository;

    @Autowired
    RestaurantRepository rRepository;

    public List<Menu> findAll() {
        return mRepository.findAll();
    }

    public Menu findById(String id) {
        Optional<Menu> menuObj = mRepository.findById(id);
        return menuObj.orElseThrow(() -> new ObjectNotFoundException("Cardápio não encontrado"));
    }

    public void createMenu(Menu menu) {
        mRepository.save(menu);
        rRepository.save(menu.getFromRestaurant());
    }

    public void removeAll() {
        mRepository.deleteAll();
    }
}
