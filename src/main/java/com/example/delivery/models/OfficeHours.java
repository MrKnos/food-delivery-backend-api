package com.example.delivery.models;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static com.google.common.base.Preconditions.checkNotNull;

public record OfficeHours(
        DayOfWeek day,
        LocalTime open,
        LocalTime close
) {
    public OfficeHours(
            DayOfWeek day,
            LocalTime open,
            LocalTime close
    ) {
        this.day = checkNotNull(day);
        this.open = checkNotNull(open);
        this.close = checkNotNull(close);
    }
}
