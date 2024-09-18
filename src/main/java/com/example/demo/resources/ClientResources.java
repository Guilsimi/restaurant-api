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

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody ClientDTO objDTO) {
        Client clientObj = cServices.fromDTO(objDTO);
        clientObj = cServices.insert(clientObj, clientObj.getPassword());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clientObj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ClientDTO objDto, @PathVariable String id) {
        Client clientObj = cServices.fromDTO(objDto);
        clientObj.setId(id);
        clientObj = cServices.update(clientObj);
        return ResponseEntity.noContent().build();
    }

    public void removeAll() {
        cServices.removeAll();
    }

    public void createClient(Client client) {
        cServices.createClient(client, client.getPassword());
    }
}
