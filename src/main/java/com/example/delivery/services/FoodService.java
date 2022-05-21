package com.example.delivery.services;

import com.example.delivery.exceptions.FoodNotFoundException;
import com.example.delivery.models.Food;
import com.example.delivery.reopositories.FoodRepositoiry;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    public FoodService(FoodRepositoiry foodRepositoiry) {
        this.foodRepositoiry = foodRepositoiry;
    }

    public FoodRepositoiry foodRepositoiry;

    public Food getFoodById(Long id) {
        return foodRepositoiry.findById(id)
                .map(Food::fromEntity)
                .orElseThrow(() -> new FoodNotFoundException(id));
    }

}
