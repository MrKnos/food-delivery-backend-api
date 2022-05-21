package com.example.delivery.exceptions;

public class FoodNotFoundException extends DataNotFoundException {

    public FoodNotFoundException(Long foodId) {
        super(String.format("Food id %s not found.", foodId));
    }

}
