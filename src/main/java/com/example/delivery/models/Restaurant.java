package com.example.delivery.models;

import com.google.common.collect.ImmutableList;

import static com.google.common.base.Preconditions.checkNotNull;

public record Restaurant(
        String name,
        Location location,
        ImmutableList<OfficeHours> officeHours,
        RestaurantType type,
        Rating rating,
        ImmutableList<Food> foods
) {
    public Restaurant(
            String name,
            Location location,
            ImmutableList<OfficeHours> officeHours,
            RestaurantType type,
            Rating rating,
            ImmutableList<Food> foods
    ) {
        this.name = checkNotNull(name);
        this.location = checkNotNull(location);
        this.officeHours = checkNotNull(officeHours);
        this.type = checkNotNull(type);
        this.rating = checkNotNull(rating);
        this.foods = checkNotNull(foods);
    }
}
