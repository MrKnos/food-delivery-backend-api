package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.models.Food;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.FoodService;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public OkResponse<String> deleteFoodById(@PathVariable Long id) {
        foodService.deleteFoodById(id);
        return OkResponse.of(ConstantMessages.SUCCESS);
    }
}
