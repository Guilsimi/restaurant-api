package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Client;
import com.example.demo.domain.Item;
import com.example.demo.domain.Menu;
import com.example.demo.domain.Order;
import com.example.demo.domain.Restaurant;
import com.example.demo.domain.enums.PaymentForm;
import com.example.demo.dto.FromRestaurantDTO;
import com.example.demo.dto.MenuRefDTO;
import com.example.demo.dto.OrderClientDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired 
    private ItemRepository itemRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void run(String... arg0) throws Exception {

        restaurantRepository.deleteAll();
        clientRepository.deleteAll();
        orderRepository.deleteAll();
        itemRepository.deleteAll();
        menuRepository.deleteAll();

        Restaurant marmitaria = new Restaurant(null, "MarmitOns", 999999999L, 
        00700700001007L, "marmitons@gmail.com", "senhadamarmitaria");

        Restaurant salgadaria = new Restaurant(null, "Salgadaria Só Filé", 991909091L,
        0050403020100L, "salgadaria@gmail.com", "senhadasalgadaria");

        Client beto = new Client(null, "Beto Pedreiro", 990909090L, 
        "BetoPedras@gmail.com", "990909090beto");

        Client bino = new Client(null, "Bino Oreia", 991234567L, 
        "BinoBetinhoJr@gmail.com", "binociladas1");

        restaurantRepository.saveAll(Arrays.asList(marmitaria, salgadaria));
        clientRepository.saveAll(Arrays.asList(beto, bino));

        Order order1 = new Order(null, 520.00, PaymentForm.PIX, "Rua Zika, 720", new OrderClientDTO(bino));
        
        orderRepository.saveAll(Arrays.asList(order1));

        Menu menuMarmitaria = new Menu(null, "Comidas", new FromRestaurantDTO(marmitaria)); 

        menuRepository.saveAll(Arrays.asList(menuMarmitaria));

        Item marmitaP = new Item(null, "Marmita P", 10.0, 
        "Marmita com Arroz e Feijão", new MenuRefDTO(menuMarmitaria));

        Item marmitaM = new Item(null, "Marmita M", 15.0, 
        "Marmita com Arroz e Feijão", new MenuRefDTO(menuMarmitaria));

        itemRepository.saveAll(Arrays.asList(marmitaP, marmitaM));

        menuMarmitaria.getItems().addAll(Arrays.asList(marmitaP, marmitaM));
        menuRepository.saveAll(Arrays.asList(menuMarmitaria));

    }

}
