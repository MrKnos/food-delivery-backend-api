package com.example.delivery.models;

import com.example.delivery.entities.FoodOptionEntity;
import com.example.delivery.forms.food.FoodOptionForm;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nullable;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public record FoodOption(
        Optional<Long> id,
        String name,
        OptionSelectionType type,
        ImmutableList<Various> various
) {
    public static FoodOption of(
            @Nullable Long id,
            String name,
            OptionSelectionType type,
            ImmutableList<Various> various
    ) {
        return new FoodOption(
                Optional.ofNullable(id),
                checkNotNull(name),
                checkNotNull(type),
                checkNotNull(various)
        );
    }

    public static FoodOption fromMock() {
        return FoodOption.of(
                null,
                "Meat tags",
                OptionSelectionType.SINGLE,
                ImmutableList.of(Various.fromMock())
        );
    }

    public static FoodOption fromEntity(FoodOptionEntity entity) {
        return FoodOption.of(
                entity.getId(),
                entity.getName(),
                entity.getSelectionType(),
                ImmutableList.copyOf(
                        checkNotNull(entity.getVarious())
                                .stream()
                                .map(Various::fromEntity)
                                .toList()
                )
        );
    }

    public static FoodOption fromForm(FoodOptionForm form) {
        return FoodOption.of(
                null,
                form.name(),
                form.type(),
                ImmutableList.copyOf(
                        form.various()
                                .stream().map(Various::fromForm)
                                .toList()
                )
        );
    }
}
