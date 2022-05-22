package com.example.delivery.exceptions.data_not_found;

public class FoodNotFoundException extends DataNotFoundException {

    public FoodNotFoundException(Long foodId) {
        super(String.format("Food id %s not found.", foodId));
    }

}
