package com.example.delivery.reopositories.jpa;

import com.example.delivery.entities.FoodOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodOptionJPA extends JpaRepository<FoodOptionEntity, Long> {
}
