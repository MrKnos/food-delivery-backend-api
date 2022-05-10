package com.example.delivery.forms;

import com.example.delivery.models.Location;
import com.example.delivery.models.OfficeHours;
import com.google.common.collect.ImmutableList;

public record RestaurantForm(
        String name,
        Location location,
        ImmutableList<OfficeHours> officeHours
) {
}
