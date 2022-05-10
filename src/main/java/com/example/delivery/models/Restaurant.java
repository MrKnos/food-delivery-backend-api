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
        ImmutableList<RestaurantTag> tags,
        Optional<Double> ratingScroll,
        ImmutableList<Food> foods
) {
    public Restaurant(
            Optional<Long> id,
            String name,
            Location location,
            ImmutableList<OfficeHours> officeHours,
            ImmutableList<RestaurantTag> tags,
            Optional<Double> ratingScroll,
            ImmutableList<Food> foods
    ) {
        this.id = id;
        this.name = checkNotNull(name);
        this.location = checkNotNull(location);
        this.officeHours = checkNotNull(officeHours);
        this.tags = tags;
        this.ratingScroll = ratingScroll;
        this.foods = checkNotNull(foods);
    }

    public static Restaurant fromMock() {
        return new Restaurant(
                Optional.empty(),
                "Knos",
                new Location(1234.0, 4321.0),
                ImmutableList.of(),
                ImmutableList.of(RestaurantTag.STREET_FOOD),
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
                // TODO: Set tags
                ImmutableList.of(),
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
