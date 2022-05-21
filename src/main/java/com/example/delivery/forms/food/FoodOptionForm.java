package com.example.delivery.forms.food;

import com.example.delivery.models.OptionSelectionType;

import java.util.List;

public record FoodOptionForm(
        String name,
        OptionSelectionType type,
        List<VariousForm> various
) {
}

