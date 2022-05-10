package com.example.delivery.forms;

import com.example.delivery.models.Location;
import com.example.delivery.models.OfficeHours;
import com.google.common.collect.ImmutableList;

public record RestaurantForm(
        String name,
        Location location,
        ImmutableList<OfficeHours> officeHours
) {

    public static RestaurantForm fromMock() {
        return new RestaurantForm(
                "Knos RT",
                new Location(9876.0, 4321.0),
                ImmutableList.of()
        );
    }
}
