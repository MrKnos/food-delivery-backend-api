package com.example.delivery.services;

import com.example.delivery.entities.FoodEntity;
import com.example.delivery.entities.FoodOptionEntity;
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

import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional
    public void deleteAllRestaurants() {
        variousRepository.deleteAll();
        foodOptionRepository.deleteAll();
        foodRepositoiry.deleteAll();
        restaurantRepository.deleteAll();
    }

    @Transactional
    public RestaurantEntity createRestaurant(Restaurant restaurant) {
        final RestaurantEntity createdRestaurant = restaurantRepository.save(
                RestaurantEntity.fromModel(restaurant)
        );

        final List<FoodEntity> createdFoods = foodRepositoiry.saveAll(
                createdRestaurant.getFoods()
        );

        final List<FoodOptionEntity> createdOptions = foodOptionRepository.saveAll(
                createdFoods
                        .stream()
                        .flatMap(food -> food.getOptions().stream())
                        .toList()
        );

        variousRepository.saveAll(
                createdOptions
                        .stream()
                        .flatMap(option -> option.getVarious().stream())
                        .toList()
        );

        return createdRestaurant;
    }
}
