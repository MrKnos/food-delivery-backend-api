package com.example.delivery.models;

import static com.google.common.base.Preconditions.checkNotNull;

public record Rating(Double ratingScroll, Double maxScroll) {
    public Rating(Double ratingScroll, Double maxScroll) {
        this.ratingScroll = checkNotNull(ratingScroll);
        this.maxScroll = checkNotNull(maxScroll);
    }
}
