package com.example.delivery.services;

import com.example.delivery.models.Restaurant;
import com.example.delivery.reopositories.RestaurantRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RestaurantService {

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    RestaurantRepository restaurantRepository;

    public ImmutableList<Restaurant> getRestaurants() {
        return ImmutableList.copyOf(
                restaurantRepository.getRestaurants()
                        .stream()
                        .map(Restaurant::fromEntity)
                        .collect(Collectors.toList())
        );
    }
}
