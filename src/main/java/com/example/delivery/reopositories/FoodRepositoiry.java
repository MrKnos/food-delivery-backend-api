package com.example.delivery.reopositories;

import com.example.delivery.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepositoiry extends JpaRepository<FoodEntity, Long> {
}
