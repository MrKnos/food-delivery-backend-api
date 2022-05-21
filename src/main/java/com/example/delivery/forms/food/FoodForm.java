package com.example.delivery.forms.food;

import java.util.List;
import java.util.Optional;

public record FoodForm(
        String name,
        String description,
        Double price,
        Optional<Double> originalPrice,
        List<FoodOptionForm> options
) {
}

