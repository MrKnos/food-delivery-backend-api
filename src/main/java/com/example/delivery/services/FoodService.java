package com.example.delivery.services;

import com.example.delivery.entities.FoodEntity;
import com.example.delivery.entities.FoodOptionEntity;
import com.example.delivery.exceptions.DataNotFoundException;
import com.example.delivery.models.Food;
import com.example.delivery.models.FoodOption;
import com.example.delivery.reopositories.FoodOptionRepository;
import com.example.delivery.reopositories.FoodRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    public FoodService(
            FoodRepository foodRepository,
            FoodOptionRepository optionRepository
    ) {
        this.foodRepository = foodRepository;
        this.optionRepository = optionRepository;
    }

    FoodRepository foodRepository;
    FoodOptionRepository optionRepository;

    public Food getFoodById(Long id) {
        return foodRepository.findById(id)
                .map(Food::fromEntity)
                .orElseThrow(() -> new DataNotFoundException(FoodEntity.class, id));
    }

    public void deleteFoodById(Long id) {
        if (!foodRepository.existsById(id)) {
            throw new DataNotFoundException(Food.class, id);
        }

        foodRepository.deleteById(id);
    }

    public void addOptions(
            Long foodId,
            ImmutableList<FoodOption> options
    ) {
        final FoodEntity food = foodRepository
                .findById(foodId)
                .orElseThrow(() -> new DataNotFoundException(FoodEntity.class, foodId));

        final List<FoodOptionEntity> optionEntities = options
                .stream().map(FoodOptionEntity::fromModel)
                .toList();

        optionEntities.forEach(option -> option.setFood(food));
        optionRepository.saveAll(optionEntities);
    }

    public void deleteFoodOptionById(Long id) {
        if (!optionRepository.existsById(id)) {
            throw new DataNotFoundException(FoodEntity.class, id);
        }

        optionRepository.deleteById(id);
    }
}
