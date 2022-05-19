package com.example.delivery.models;

import com.example.delivery.requests.RestaurantPredicateRequest;

import java.util.Optional;

public record RestaurantPredicate(Optional<RestaurantTag> tag) {

    public static RestaurantPredicate fromRequest(
            RestaurantPredicateRequest request
    ) {
        return new RestaurantPredicate(
                request.tag().map(RestaurantTag::valueOf)
        );
    }
}
