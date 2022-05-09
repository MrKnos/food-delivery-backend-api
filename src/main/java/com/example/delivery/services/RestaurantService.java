package com.example.delivery.services;

import com.example.delivery.entities.FoodEntity;
import com.example.delivery.entities.FoodOptionEntity;
import com.example.delivery.entities.RestaurantEntity;
import com.example.delivery.entities.VariousEntity;
import com.example.delivery.models.Restaurant;
import com.example.delivery.reopositories.FoodOptionRepository;
import com.example.delivery.reopositories.FoodRepositoiry;
import com.example.delivery.reopositories.RestaurantRepository;
import com.example.delivery.reopositories.VariousRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
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

    public Optional<Restaurant> findRestaurantById(Long id) {
        return restaurantRepository.findById(id).map(Restaurant::fromEntity);
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
        final RestaurantEntity entity = RestaurantEntity.fromModel(restaurant);
        final RestaurantEntity createdRestaurant = restaurantRepository.save(entity);

        createdRestaurant.setFoods(createFoods(createdRestaurant));

        return createdRestaurant;
    }

    @Transactional
    List<FoodEntity> createFoods(RestaurantEntity restaurant) {
        final List<FoodEntity> foods = restaurant.getFoods();

        foods.forEach(food -> food.setRestaurant(restaurant));
        final List<FoodEntity> createdFoods = foodRepositoiry.saveAll(foods);

        createdFoods.forEach(food -> food.setOptions(createFoodOptions(food)));

        return createdFoods;
    }

    @Transactional
    List<FoodOptionEntity> createFoodOptions(FoodEntity food) {
        final List<FoodOptionEntity> options = food.getOptions();
        options.forEach(option -> option.setFood(food));

        final List<FoodOptionEntity> createdOptions = foodOptionRepository.saveAll(options);
        createdOptions.forEach(option -> option.setVarious(createVarious(option)));

        return createdOptions;
    }

    List<VariousEntity> createVarious(FoodOptionEntity option) {
        final List<VariousEntity> various = option.getVarious();
        various.forEach(_various -> _various.setOption(option));

        return variousRepository.saveAll(various);
    }
}
