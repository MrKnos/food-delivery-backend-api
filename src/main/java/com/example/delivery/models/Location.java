package com.example.delivery.models;

import static com.google.common.base.Preconditions.checkNotNull;

public record Location(
        Double latitude,
        Double longitude
) {
    public Location(Double latitude, Double longitude) {
        this.latitude = checkNotNull(latitude);
        this.longitude = checkNotNull(longitude);
    }
}
