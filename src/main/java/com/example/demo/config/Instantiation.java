package com.example.demo.config;

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

                // ---------------------- INSTANCIANDO COISAS DO LADO RESTAURANTE ---------------------- //
                // INSTANCIANDO UM RESTAURANTE

                Restaurant restaurant = new Restaurant(null, "Moda da Carne", 33939939L,
                                12345678911L, "modacarne@gmail.com", "modadacarnerestaurante1");

                rResources.createRestaurant(restaurant);

                // INSTANCIANDO DOIS CARDÁPIOS

                Menu menu = new Menu(null, "Carnes", restaurant);

                Menu menu2 = new Menu(null, "Bevidas", restaurant);

                mResources.createMenu(menu);
                mResources.createMenu(menu2);

                // INSTANCIANDO DOIS ITENS PARA CADA CARDÁPIO

                Item item = new Item(null, "Picanha Bovina", 110.00, 
                "500g de picanha bovina assada", menu);

                Item item2 = new Item(null, "Bisteca Suína", 40.00, 
                "500g de bisteca suína assada", menu);

                Item item3 = new Item(null, "Suco de Laranja", 10.00, 
                "350ml de suco natural de laranja", menu2);

                Item item4 = new Item(null, "Coca-Cola", 7.50, 
                "Garrada de 1 litro", menu2);

                iResources.createItems(item);
                iResources.createItems(item2);
                iResources.createItems(item3);
                iResources.createItems(item4);

                // ---------------------- INSTANCIANDO COISAS DO LADO RESTAURANTE ---------------------- //
                // INSTANCIANDO UM CLIENTE

                Client client = new Client(null, "Juca Silva", 998998898L, 
                "jocasilva@gmail.com", "senhadojucasilva");

                cResources.createClient(client);

                // INSTANCIANDO UM PEDIDO

                Order order = new Order(null, PaymentForm.DINHEIRO, "Rua do Juca, 1489", client);

                oResources.createOrders(order);

                // INSTANCIANDO ITENS DO PEDIDO

                OrderItem orderItem = new OrderItem(item, order, 1);
                OrderItem orderItem1 = new OrderItem(item2, order, 1);
                OrderItem orderItem2 = new OrderItem(item3, order, 3);
                OrderItem orderItem3 = new OrderItem(item4, order, 1);

                oiResources.createOrderItems(orderItem);
                oiResources.createOrderItems(orderItem1);
                oiResources.createOrderItems(orderItem2);
                oiResources.createOrderItems(orderItem3);
        }

}
