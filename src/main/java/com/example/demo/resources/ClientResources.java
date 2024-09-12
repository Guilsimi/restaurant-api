package com.example.demo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Client;
import com.example.demo.dto.ClientDTO;
import com.example.demo.services.ClientServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

    @Autowired
    private ClientServices cServices;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> cList = cServices.findAll();
        List<ClientDTO> cDtoList = cList.stream().map(clients -> new ClientDTO(clients)).collect(Collectors.toList());
        return ResponseEntity.ok().body(cDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable String id) {
        Client cObj = cServices.findById(id);
        return ResponseEntity.ok().body(new ClientDTO(cObj));
    }

    public void removeAll() {
        cServices.removeAll();
    }

    public void createClient(Client client) {
        cServices.createClient(client);
    }
}
