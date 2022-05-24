package com.example.delivery.forms;

import java.time.LocalTime;

public record RestaurantOfficeHoursForm(
        String day,
        LocalTime open,
        LocalTime close
) {
}
