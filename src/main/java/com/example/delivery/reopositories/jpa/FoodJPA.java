package com.example.delivery.reopositories.jpa;

import com.example.delivery.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodJPA extends JpaRepository<FoodEntity, Long> {
}
