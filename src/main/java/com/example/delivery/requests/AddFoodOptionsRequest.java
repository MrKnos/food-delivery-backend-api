package com.example.delivery.requests;

import com.example.delivery.forms.food.FoodOptionForm;

import java.util.List;

public record AddFoodOptionsRequest(List<FoodOptionForm> options) {
}
