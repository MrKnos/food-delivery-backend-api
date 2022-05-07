package com.example.delivery.models;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public record Food(
        String name,
        String description,
        Double price,
        Optional<Double> originalPrice,
        String options
) {
    public Food(
            String name,
            String description,
            Double price,
            Optional<Double> originalPrice,
            String options
    ) {
        this.name = checkNotNull(name);
        this.description = checkNotNull(description);
        this.price = checkNotNull(price);
        this.originalPrice = originalPrice;
        this.options = checkNotNull(options);
    }
}
