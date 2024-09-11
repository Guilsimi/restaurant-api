package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Menu;
import com.example.demo.repository.MenuRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class MenuServices {

    @Autowired
    private MenuRepository mRepository;

    public List<Menu> findAll() {
        return mRepository.findAll();
    }

    public Menu findById(String id) {
        Optional<Menu> menuObj = mRepository.findById(id);
        return menuObj.orElseThrow(() -> new ObjectNotFoundException("Cardápio não encontrado"));
    } 
}
