package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Client;
import com.example.demo.domain.Restaurant;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RestaurantRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void run(String... arg0) throws Exception {

        restaurantRepository.deleteAll();
        clientRepository.deleteAll();

        Restaurant marmitaria = new Restaurant(null, "MarmitOns", 999999999L, 
        00700700001007L, "marmitons@gmail.com", "senhadamarmitaria");

        Client beto = new Client(null, "Beto Pedreiro", 990909090L, 
        "BetoPedras@gmail.com", "990909090beto");

        Client bino = new Client(null, "Bino Oreia", 991234567L, 
        "BinoBetinhoJr@gmail.com", "binociladas1");

        restaurantRepository.saveAll(Arrays.asList(marmitaria));
        clientRepository.saveAll(Arrays.asList(beto, bino));
    }

}
