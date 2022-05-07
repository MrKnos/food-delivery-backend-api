package com.example.delivery.models;

import static com.google.common.base.Preconditions.checkNotNull;

public record Various(String name, Double price) {
    public Various(String name, Double price) {
        this.name = checkNotNull(name);
        this.price = checkNotNull(price);
    }
}
