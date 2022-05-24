package com.example.delivery.models;

import com.example.delivery.entities.OfficeHoursEntity;
import com.example.delivery.forms.RestaurantOfficeHoursForm;

import javax.annotation.Nullable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public record OfficeHours(
        Optional<Long> id,
        DayOfWeek day,
        LocalTime open,
        LocalTime close
) {
    public static OfficeHours of(
            @Nullable Long id,
            DayOfWeek day,
            LocalTime open,
            LocalTime close
    ) {
        return new OfficeHours(
                Optional.ofNullable(id),
                checkNotNull(day),
                checkNotNull(open),
                checkNotNull(close)
        );
    }

    public static OfficeHours fromMock() {
        return OfficeHours.of(
                null,
                DayOfWeek.SUNDAY,
                LocalTime.now(),
                LocalTime.now().plusHours(8)
        );
    }

    public static OfficeHours fromEntity(OfficeHoursEntity entity) {
        return OfficeHours.of(
                entity.getId(),
                DayOfWeek.valueOf(entity.getDay()),
                entity.getOpen(),
                entity.getClose()
        );
    }

    public static OfficeHours fromForm(RestaurantOfficeHoursForm form) {
        return  OfficeHours.of(
                null,
                DayOfWeek.valueOf(form.day().toUpperCase()),
                form.open(),
                form.close()
        );
    }

}
