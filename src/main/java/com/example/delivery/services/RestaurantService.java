package com.example.delivery.services;

import com.example.delivery.entities.RestaurantEntity;
import com.example.delivery.exceptions.RestaurantNotFoundException;
import com.example.delivery.models.Food;
import com.example.delivery.models.Restaurant;
import com.example.delivery.reopositories.FoodOptionRepository;
import com.example.delivery.reopositories.FoodRepositoiry;
import com.example.delivery.reopositories.RestaurantRepository;
import com.example.delivery.reopositories.VariousRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class RestaurantService {

    public RestaurantService(
            RestaurantRepository restaurantRepository,
            FoodRepositoiry foodRepositoiry,
            FoodOptionRepository foodOptionRepository,
            VariousRepository variousRepository
    ) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepositoiry = foodRepositoiry;
        this.foodOptionRepository = foodOptionRepository;
        this.variousRepository = variousRepository;
    }

    RestaurantRepository restaurantRepository;
    FoodRepositoiry foodRepositoiry;
    FoodOptionRepository foodOptionRepository;
    VariousRepository variousRepository;

    public ImmutableList<Restaurant> getRestaurants() {
        return ImmutableList.copyOf(
                restaurantRepository.findAll()
                        .stream()
                        .map(Restaurant::fromEntity)
                        .collect(Collectors.toList())
        );
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository
                .findById(id)
                .map(Restaurant::fromEntity)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
    }

    public ImmutableList<Food> listFoodsInRestaurant(Long restaurantId) {
        return ImmutableList.copyOf(
                getRestaurantById(restaurantId).foods()
        );
    }

    public void deleteAllRestaurants() {
        restaurantRepository.deleteAll();
    }

    public void deleteRestaurantById(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new RestaurantNotFoundException(id);
        }

        restaurantRepository.deleteById(id);
    }

    public RestaurantEntity createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(
                RestaurantEntity.fromModel(restaurant)
        );
    }
}
