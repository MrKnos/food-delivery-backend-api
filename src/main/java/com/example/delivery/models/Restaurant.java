package com.example.delivery.models;

import com.example.delivery.entities.RestaurantEntity;
import com.google.common.collect.ImmutableList;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public record Restaurant(
        Optional<Long> id,
        String name,
        Location location,
        ImmutableList<OfficeHours> officeHours,
        // TODO: Change to list of restaurant tag
        RestaurantType type,
        Optional<Double> ratingScroll,
        ImmutableList<Food> foods
) {
    public Restaurant(
            Optional<Long> id,
            String name,
            Location location,
            ImmutableList<OfficeHours> officeHours,
            RestaurantType type,
            Optional<Double> ratingScroll,
            ImmutableList<Food> foods
    ) {
        this.id = id;
        this.name = checkNotNull(name);
        this.location = checkNotNull(location);
        this.officeHours = checkNotNull(officeHours);
        this.type = type;
        this.ratingScroll = ratingScroll;
        this.foods = checkNotNull(foods);
    }

    public static Restaurant fromMock() {
        return new Restaurant(
                Optional.empty(),
                "Knos",
                new Location(1234.0, 4321.0),
                ImmutableList.of(),
                RestaurantType.STREET,
                Optional.of(80.0),
                ImmutableList.of(Food.fromMock())
        );
    }

    public static Restaurant fromEntity(RestaurantEntity entity) {
        return new Restaurant(
                Optional.of(entity.getId()),
                checkNotNull(entity.getName()),
                new Location(
                        checkNotNull(entity.getLatitude()),
                        checkNotNull(entity.getLongitude())
                ),
                ImmutableList.of(),
                entity.getType(),
                Optional.ofNullable(entity.getRatingScroll()),
                ImmutableList.copyOf(
                        entity.getFoods()
                                .stream()
                                .map(Food::fromEntity)
                                .collect(Collectors.toList())
                )
        );
    }
}
