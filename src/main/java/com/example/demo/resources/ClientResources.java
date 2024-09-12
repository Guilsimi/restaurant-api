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
import com.example.demo.dto.ClientsDTO;
import com.example.demo.services.ClientServices;

import jakarta.annotation.Resource;

@Resource
@RestController
@RequestMapping(value = "/clients")
public class ClientResources {

    @Autowired
    private ClientServices cServices;

    @GetMapping
    public ResponseEntity<List<ClientsDTO>> findAll() {
        List<Client> cList = cServices.findAll();
        List<ClientsDTO> cDtoList = cList.stream().map(clients -> new ClientsDTO(clients)).collect(Collectors.toList());
        return ResponseEntity.ok().body(cDtoList);
    }

    @GetMapping(value = "/{id}") 
    public ResponseEntity<ClientsDTO> findById(@PathVariable String id) {
        Client cObj = cServices.findById(id);
        return ResponseEntity.ok().body(new ClientsDTO(cObj));
    }

    public void removeAll() {
        cServices.removeAll();
     }
}
