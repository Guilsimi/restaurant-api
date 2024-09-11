package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Client;
import com.example.demo.domain.Item;
import com.example.demo.domain.Menu;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Restaurant;
import com.example.demo.domain.enums.PaymentForm;
import com.example.demo.dto.FromRestaurantDTO;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MenuRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.resources.ItemResources;
import com.example.demo.resources.OrderResources;

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

        @Autowired
        private OrderItemRepository orderItemRepository;

        @Autowired 
        OrderResources oResources;

        @Autowired
        ItemResources iResources;

        @Override
        public void run(String... arg0) throws Exception {

                restaurantRepository.deleteAll();
                clientRepository.deleteAll();
                orderRepository.deleteAll();
                itemRepository.deleteAll();
                menuRepository.deleteAll();
                orderItemRepository.deleteAll();

                Restaurant restaurant = new Restaurant(null, "Teste", 1234L, 15457L, "teste@gmail.com", "123");

                restaurantRepository.save(restaurant);

                Menu menu = new Menu(null, "Rango", new FromRestaurantDTO(restaurant));

                menuRepository.save(menu);

                Item item = new Item(null, "Item teste", 25.20, "descrisao", menu);

                iResources.createItems(item);
        }

}
