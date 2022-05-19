package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.forms.RestaurantForm;
import com.example.delivery.models.Food;
import com.example.delivery.models.Restaurant;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.RestaurantService;
import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantController {
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    RestaurantService restaurantService;

    @PostMapping("/restaurant")
    public OkResponse<Restaurant> createRestaurant(@RequestBody RestaurantForm form) {
        return OkResponse.of(restaurantService.createRestaurantFromForm(form));
    }

    @GetMapping("/restaurants")
    public OkResponse<ImmutableList<Restaurant>> getRestaurants() {
        return OkResponse.of(restaurantService.getRestaurants());
    }

    @GetMapping("/restaurants/{id}")
    public OkResponse<Restaurant> getRestaurant(@PathVariable Long id) {
        return OkResponse.of(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/restaurants/{id}/foods")
    public OkResponse<ImmutableList<Food>> getFoods(@PathVariable Long id) {
        return OkResponse.of(restaurantService.listFoodsInRestaurant(id));
    }

    @DeleteMapping("/restaurants/{id}")
    public OkResponse<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
        return OkResponse.of(ConstantMessages.SUCCESS);
    }
}
