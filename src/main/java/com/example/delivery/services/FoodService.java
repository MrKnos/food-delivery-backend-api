package com.example.delivery.services;

import com.example.delivery.exceptions.FoodNotFoundException;
import com.example.delivery.models.Food;
import com.example.delivery.reopositories.FoodRepository;
import org.springframework.stereotype.Service;

@Service
public class FoodService {

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public FoodRepository foodRepository;

    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .map(Food::fromEntity)
                .orElseThrow(() -> new FoodNotFoundException(id));
    }

    public void deleteFoodById(Long id) {
        if (!foodRepository.existsById(id)) {
            throw new FoodNotFoundException(id);
        }

        foodRepository.deleteById(id);
    }
}
