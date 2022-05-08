package com.example.delivery.models;

import com.example.delivery.entities.FoodOptionEntity;
import com.google.common.collect.ImmutableList;

import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public record FoodOption(
        String name,
        OptionSelectionType type,
        ImmutableList<Various> various
) {
    public FoodOption(
            String name,
            OptionSelectionType type,
            ImmutableList<Various> various
    ) {
        this.name = checkNotNull(name);
        this.type = checkNotNull(type);
        this.various = checkNotNull(various);
    }

    public static FoodOption fromMock() {
        return new FoodOption(
                "Meat type",
                OptionSelectionType.SINGLE,
                ImmutableList.of(Various.fromMock())
        );
    }

    public static FoodOption fromEntity(FoodOptionEntity entity) {
        return new FoodOption(
                entity.getName(),
                entity.getSelectionType(),
                ImmutableList.copyOf(
                        checkNotNull(entity.getVarious())
                                .stream()
                                .map(Various::fromEntity)
                                .collect(Collectors.toList())
                )
        );
    }
}
