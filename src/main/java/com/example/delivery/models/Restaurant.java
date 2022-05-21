package com.example.delivery.models;

import com.example.delivery.entities.RestaurantEntity;
import com.google.common.collect.ImmutableList;

import java.util.Optional;

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
                ImmutableList.copyOf(
                        entity.getTags()
                                .stream().map(RestaurantTag::fromEntity)
                                .toList()
                ),
                Optional.ofNullable(entity.getRatingScroll()),
                ImmutableList.copyOf(
                        entity.getFoods()
                                .stream()
                                .map(Food::fromEntity)
                                .toList()
                )
        );
    }

    public Boolean predicate(RestaurantPredicate predicate) {
        if (predicate.tag().isPresent()) {
            return hasTag(predicate.tag().get());
        }

        return true;
    }

    public Boolean hasTag(RestaurantTag tag) {
        return tags().contains(tag);
    }
}
