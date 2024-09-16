package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Client;
import com.example.demo.dto.ClientDTO;
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

    public Client insert(Client clientObj) {
        return cRepository.insert(clientObj);
    }

    public void delete(String id) {
        findById(id);
        cRepository.deleteById(id);
    }

    public Client update(Client clientObj) {
        Client newClientObj = findById(clientObj.getId());
        updateData(newClientObj, clientObj);
        return cRepository.save(newClientObj);
    }

    public void updateData(Client newClientObj, Client clientObj) {
        newClientObj.setName(clientObj.getName() != null ? clientObj.getName() : newClientObj.getName());
        newClientObj.setEmail(clientObj.getEmail() != null ? clientObj.getEmail() : newClientObj.getEmail());
        newClientObj.setPhone(clientObj.getPhone() != null ? clientObj.getPhone() : newClientObj.getPhone());
        newClientObj.setPassword(clientObj.getPassword() != null ? clientObj.getPassword() : newClientObj.getPassword());
    }

    public void removeAll() {
        cRepository.deleteAll();
    }

    public void createClient(Client client) {
        cRepository.save(client);
    }

    public Client fromDTO(ClientDTO objDto) {
        return new Client(objDto.getId(), objDto.getName(), objDto.getPhone(),
                objDto.getEmail(), objDto.getPassword());
    }
}
