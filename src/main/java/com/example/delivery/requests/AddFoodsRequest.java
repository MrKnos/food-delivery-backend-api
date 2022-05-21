package com.example.delivery.requests;

import com.example.delivery.forms.food.FoodForm;

import java.util.List;

public record AddFoodsRequest(List<FoodForm> foods) {
}