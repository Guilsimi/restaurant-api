package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.resources.ClientResources;
import com.example.demo.resources.ItemResources;
import com.example.demo.resources.MenuResources;
import com.example.demo.resources.OrderItemResources;
import com.example.demo.resources.OrderResources;
import com.example.demo.resources.RestaurantResources;

@Configuration
public class Instantiation implements CommandLineRunner {

        @Autowired
        private RestaurantResources rResources;

        @Autowired
        private ClientResources cResources;

        @Autowired 
        private OrderResources oResources;

        @Autowired
        private ItemResources iResources;

        @Autowired
        private MenuResources mResources;

        @Autowired
        private OrderItemResources oiResources;

        @Override
        public void run(String... arg0) throws Exception {

                rResources.removeAll();
                cResources.removeAll();
                oResources.removeAll();
                iResources.removeAll();
                mResources.removeAll();
                oiResources.removeAll();

        }

}
