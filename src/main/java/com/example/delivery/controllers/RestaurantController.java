package com.example.delivery.controllers;

import com.example.delivery.models.Restaurant;
import com.example.delivery.services.RestaurantService;
import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ImmutableList<Restaurant> getRestaurants() {
        return restaurantService.getRestaurants();
    }
}
