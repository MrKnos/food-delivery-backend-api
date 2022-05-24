package com.example.delivery.reopositories;

import com.example.delivery.entities.OfficeHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OfficeHoursRepository extends JpaRepository<OfficeHoursEntity, Long> {

    Optional<OfficeHoursEntity> findByRestaurantIdAndDay(Long restaurantId, String day);

    void deleteByRestaurantIdAndDay(Long restaurantId, String day);

    Boolean existsByRestaurantIdAndDay(Long restaurantId, String day);

}
