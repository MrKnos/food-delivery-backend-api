package com.example.delivery.entities;

import com.example.delivery.models.OfficeHours;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "OFFICE_HOURS")
public class OfficeHoursEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    RestaurantEntity restaurant;

    @Column(name = "day")
    String day;

    @Column(name = "open")
    LocalTime open;

    @Column(name = "close")
    LocalTime close;

    public static OfficeHoursEntity of(
            @Nullable Long id,
            String day,
            LocalTime open,
            LocalTime close
    ) {
        final OfficeHoursEntity entity = new OfficeHoursEntity();
        entity.setId(id);
        entity.setDay(day);
        entity.setOpen(open);
        entity.setClose(close);

        return entity;
    }

    public static OfficeHoursEntity fromModel(OfficeHours officeHours) {
        return OfficeHoursEntity.of(
                null,
                officeHours.day().name(),
                officeHours.open(),
                officeHours.close()
        );
    }
}
