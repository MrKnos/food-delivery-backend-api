package com.example.delivery.exceptions.data_not_found;

import java.time.DayOfWeek;

public class OfficeHoursNotFoundException extends DataNotFoundException {

    public OfficeHoursNotFoundException(Long restaurantId, DayOfWeek day) {
        super(
                String.format(
                        "%s office hours of restaurant id %s not found.",
                        day.name(),
                        restaurantId
                )
        );
    }

}
