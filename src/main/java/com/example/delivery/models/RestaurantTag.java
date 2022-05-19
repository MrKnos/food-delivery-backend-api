package com.example.delivery.models;

import com.example.delivery.entities.TagEntity;

public enum RestaurantTag {
    STREET_FOOD,
    EXCLUSIVE;

    public static RestaurantTag fromEntity(TagEntity tag) {
        return RestaurantTag.valueOf(tag.getName());
    }
}