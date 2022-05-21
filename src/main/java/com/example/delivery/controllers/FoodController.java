package com.example.delivery.controllers;

import com.example.delivery.models.Food;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.FoodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/foods")
public class FoodController {

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    FoodService foodService;

    @GetMapping("/{id}")
    public OkResponse<Food> getFoodById(@PathVariable Long id) {
        return OkResponse.of(foodService.getFoodById(id));
    }
}
