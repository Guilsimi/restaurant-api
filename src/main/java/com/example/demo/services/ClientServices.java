package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Client;
import com.example.demo.repository.ClientRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class ClientServices {

    @Autowired
    private ClientRepository cRepository;

    public List<Client> findAll() {
        return cRepository.findAll();
    }

    public Client findById(String id) {
        Optional<Client> client = cRepository.findById(id);
        return client.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }
}
