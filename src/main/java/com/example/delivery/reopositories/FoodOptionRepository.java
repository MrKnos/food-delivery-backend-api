package com.example.delivery.reopositories;

import com.example.delivery.entities.FoodOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOptionRepository extends JpaRepository<FoodOptionEntity, Long> {
}
