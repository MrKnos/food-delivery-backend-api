package com.example.delivery.exceptions.data_not_found;

public class FoodOptionNotFoundException extends DataNotFoundException {

    public FoodOptionNotFoundException(Long foodOptionId) {
        super(String.format("Food option id %s not found.", foodOptionId));
    }

}
