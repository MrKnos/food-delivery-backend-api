package com.example.delivery.models;

import com.example.delivery.entities.VariousEntity;
import com.example.delivery.forms.food.VariousForm;

import static com.google.common.base.Preconditions.checkNotNull;

public record Various(String name, Double price) {

    public static Various of(String name, Double price) {
        return new Various(
                checkNotNull(name),
                checkNotNull(price)
        );
    }

    public static Various fromMock() {
        return new Various(
                "Pork",
                20.0
        );
    }

    public static Various fromEntity(VariousEntity entity) {
        return new Various(
                checkNotNull(entity.getName()),
                checkNotNull(entity.getPrice())
        );
    }

    public static Various fromForm(VariousForm form) {
        return Various.of(form.name(), form.price());
    }
}
