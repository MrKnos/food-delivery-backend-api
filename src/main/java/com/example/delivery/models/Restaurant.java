package com.example.delivery.models;

import com.example.delivery.entities.RestaurantEntity;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nullable;

import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public record Restaurant(
        String name,
        Location location,
        ImmutableList<OfficeHours> officeHours,
        RestaurantType type,
        // TODO: Change ratingScroll to nullable
        @Nullable
        Double ratingScroll,
        ImmutableList<Food> foods
) {
    public Restaurant(
            String name,
            Location location,
            ImmutableList<OfficeHours> officeHours,
            RestaurantType type,
            @Nullable
                    Double ratingScroll,
            ImmutableList<Food> foods
    ) {
        this.name = checkNotNull(name);
        this.location = checkNotNull(location);
        this.officeHours = checkNotNull(officeHours);
        this.type = checkNotNull(type);
        this.ratingScroll = checkNotNull(ratingScroll);
        this.foods = checkNotNull(foods);
    }

    public static Restaurant fromMock() {
        return new Restaurant(
                "Knos",
                new Location(1234.0, 4321.0),
                ImmutableList.of(),
                RestaurantType.STREET,
                80.0,
                ImmutableList.of(Food.fromMock())
        );
    }

    public static Restaurant fromEntity(RestaurantEntity entity) {
        return new Restaurant(
                checkNotNull(entity.getName()),
                new Location(
                        checkNotNull(entity.getLatitude()),
                        checkNotNull(entity.getLongitude())
                ),
                ImmutableList.of(),
                checkNotNull(entity.getType()),
                entity.getRatingScroll(),
                ImmutableList.copyOf(
                        entity.getFoods()
                                .stream()
                                .map(Food::fromEntity)
                                .collect(Collectors.toList())
                )
        );
    }
}
