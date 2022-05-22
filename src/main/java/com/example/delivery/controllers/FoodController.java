package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.models.Food;
import com.example.delivery.models.FoodOption;
import com.example.delivery.requests.AddFoodOptionsRequest;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.FoodService;
import com.google.common.collect.ImmutableList;
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

    @PostMapping("/{id}/options")
    public OkResponse<String>  addFoodOptions(
           @PathVariable Long id,
           @RequestBody AddFoodOptionsRequest request
    ) {
        foodService.addOptions(
                id,
                ImmutableList.copyOf(
                        request.options()
                                .stream().map(FoodOption::fromForm)
                                .toList()
                )
        );
        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @DeleteMapping("/options/{id}")
    public OkResponse<String> deleteFoodOptionById(@PathVariable Long id) {
        foodService.deleteFoodOptionById(id);
        return OkResponse.of(ConstantMessages.SUCCESS);
    }
}
