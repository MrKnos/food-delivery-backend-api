package com.example.delivery.models;

import com.example.delivery.entities.FoodEntity;
import com.example.delivery.forms.food.FoodForm;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nullable;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public record Food(
        Optional<Long> id,
        String name,
        String description,
        Double price,
        Optional<Double> originalPrice,
        ImmutableList<FoodOption> options
) {
    public static Food of(
            @Nullable Long id,
            String name,
            String description,
            Double price,
            @Nullable Double originalPrice,
            ImmutableList<FoodOption> options
    ) {
        return new Food(
                Optional.ofNullable(id),
                checkNotNull(name),
                checkNotNull(description),
                checkNotNull(price),
                Optional.ofNullable(originalPrice),
                checkNotNull(options)
        );
    }

    public static Food fromEntity(FoodEntity entity) {
        return Food.of(
                entity.getId(),
                checkNotNull(entity.getName()),
                checkNotNull(entity.getDescription()),
                checkNotNull(entity.getPrice()),
                entity.getOriginalPrice(),
                ImmutableList.copyOf(
                        entity.getOptions()
                                .stream()
                                .map(FoodOption::fromEntity)
                                .collect(Collectors.toList())
                )
        );
    }

    public static Food fromForm(FoodForm form) {
        return Food.of(
                null,
                form.name(),
                form.description(),
                form.price(),
                form.originalPrice().orElse(null),
                ImmutableList.copyOf(
                        form.options()
                                .stream().map(FoodOption::fromForm)
                                .toList()
                )
        );
    }

    public static Food fromMock() {
        final Double randomPrice = randomPrice();

        return new Food(
                null,
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
