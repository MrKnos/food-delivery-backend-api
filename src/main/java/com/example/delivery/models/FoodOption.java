package com.example.delivery.models;

import com.google.common.collect.ImmutableList;

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
}
