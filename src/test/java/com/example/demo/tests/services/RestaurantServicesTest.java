package com.example.demo.tests.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.domain.Restaurant;
import com.example.demo.repository.RestaurantRepository;
import com.example.demo.services.RestaurantServices;
import com.example.demo.services.exception.ObjectNotFoundException;

@ExtendWith(MockitoExtension.class)
public class RestaurantServicesTest {

    @InjectMocks
    RestaurantServices services;

    @Mock
    RestaurantRepository repository;

    Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant("idmanual", "Restaurante Teste 1", 1111111L,
        111111111L, "teste1@gmail.com", "senha");
    }

    @Test
    void returnListOfAllRestaurantsInFindAllMethod() {
        when(repository.findAll()).thenReturn(Collections.singletonList(restaurant));

        List<Restaurant> restList = services.findAll();

        assertEquals(Collections.singletonList(restaurant), restList);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void returnRestaurantById() {
        Optional<Restaurant> restaurantOpt = Optional.ofNullable(restaurant);
        when(repository.findById(restaurant.getId())).thenReturn(restaurantOpt);

        services.findById("idmanual");
        verify(repository).findById(restaurant.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void throwExceptionWhenObjectRestaurantIsNotFoundById() {
        final ObjectNotFoundException e = assertThrows(ObjectNotFoundException.class, () -> {
            services.findById("idinexistente");
        });

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), containsString("Restaurante não encontrado"));
        verify(repository).findById("idinexistente");
        verifyNoMoreInteractions(repository);
    }

    @Test
    void returnRestaurantByEmail() {
        Optional<Restaurant> restaurantOpt = Optional.ofNullable(restaurant);
        when(repository.findByEmail(restaurant.getEmail())).thenReturn(restaurantOpt);

        services.findByEmail("teste1@gmail.com");
        verify(repository).findByEmail(restaurant.getEmail());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void throwExceptionWhenObjectRestaurantIsNotFoundByEmail() {
        final ObjectNotFoundException e = assertThrows(ObjectNotFoundException.class, () -> {
            services.findByEmail("emailinexsitente@gmail.com");
        });

        assertThat(e, notNullValue());
        assertThat(e.getMessage(), containsString("Restaurante não encontrado"));
        verify(repository).findByEmail("emailinexsitente@gmail.com");
        verifyNoMoreInteractions(repository);
    }
}
