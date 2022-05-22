package com.example.delivery.forms;

import com.example.delivery.models.Location;

public record RestaurantForm(
        String name,
        Location location
) {

    public static RestaurantForm fromMock() {
        return new RestaurantForm(
                "Knos RT",
                new Location(9876.0, 4321.0)
        );
    }
}
