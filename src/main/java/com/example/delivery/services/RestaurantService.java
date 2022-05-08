package com.example.delivery.services;

import com.example.delivery.models.Restaurant;
import com.example.delivery.reopositories.RestaurantRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public Restaurant getRestaurantById(Long id) {
        // TODO: Throw DataNotFoundException
        return findRestaurantById(id).orElseThrow();
    }

    public Optional<Restaurant> findRestaurantById(Long id) {
        return restaurantRepository.findRestaurantById(id).map(Restaurant::fromEntity);
    }
}
