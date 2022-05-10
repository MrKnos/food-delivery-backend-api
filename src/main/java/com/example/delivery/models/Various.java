package com.example.delivery.models;

import com.example.delivery.entities.VariousEntity;

import static com.google.common.base.Preconditions.checkNotNull;

public record Various(String name, Double price) {
    public Various(String name, Double price) {
        this.name = checkNotNull(name);
        this.price = checkNotNull(price);
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
}
