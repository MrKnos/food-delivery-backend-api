package com.example.delivery.exceptions;

public class RestaurantNotFoundException extends DataNotFoundException {

    public RestaurantNotFoundException(Long restaurantId) {
        super(String.format("Restaurant id %s not found.", restaurantId));
    }

}