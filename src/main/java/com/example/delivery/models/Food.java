package com.example.delivery.models;

import com.example.delivery.entities.FoodEntity;
import com.google.common.collect.ImmutableList;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public record Food(
        String name,
        String description,
        Double price,
        Optional<Double> originalPrice,
        ImmutableList<FoodOption> options
) {
    public Food(
            String name,
            String description,
            Double price,
            Optional<Double> originalPrice,
            ImmutableList<FoodOption> options
    ) {
        this.name = checkNotNull(name);
        this.description = checkNotNull(description);
        this.price = checkNotNull(price);
        this.originalPrice = originalPrice;
        this.options = checkNotNull(options);
    }

    public static Food fromEntity(FoodEntity entity) {
        return new Food(
                checkNotNull(entity.getName()),
                checkNotNull(entity.getDescription()),
                checkNotNull(entity.getPrice()),
                Optional.ofNullable(entity.getOriginalPrice()),
                ImmutableList.copyOf(
                        entity.getOptions()
                                .stream()
                                .map(FoodOption::fromEntity)
                                .collect(Collectors.toList())
                )
        );
    }

    public static Food fromMock() {
        final Double randomPrice = randomPrice();

        return new Food(
                "Tom Yum",
                "Thai spicy soup",
                randomPrice,
                Optional.of(randomPrice + 40),
                ImmutableList.of(FoodOption.fromMock())
        );
    }

    private static Double randomPrice() {
        final Random random = new Random();
        final Double randomPrice = random.nextDouble(100);

        final DecimalFormat formatter = new DecimalFormat("0.00");
        return Double.valueOf(formatter.format(randomPrice));
    }
}
