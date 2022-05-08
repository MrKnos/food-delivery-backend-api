package com.example.delivery.reopositories.jpa;

import com.example.delivery.entities.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantJPA extends JpaRepository<RestaurantEntity, Long> {
}