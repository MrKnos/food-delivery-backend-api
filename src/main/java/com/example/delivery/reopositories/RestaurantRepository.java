package com.example.delivery.reopositories;

import com.example.delivery.entities.FoodEntity;
import com.example.delivery.entities.FoodOptionEntity;
import com.example.delivery.entities.RestaurantEntity;
import com.example.delivery.entities.VariousEntity;
import com.example.delivery.models.Restaurant;
import com.example.delivery.reopositories.jpa.FoodJPA;
import com.example.delivery.reopositories.jpa.FoodOptionJPA;
import com.example.delivery.reopositories.jpa.RestaurantJPA;
import com.example.delivery.reopositories.jpa.VariousJPA;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RestaurantRepository {

    public RestaurantRepository(RestaurantJPA restaurantJPA, FoodJPA foodJPA, FoodOptionJPA foodOptionJPA, VariousJPA variousJPA) {
        this.restaurantJPA = restaurantJPA;
        this.foodJPA = foodJPA;
        this.foodOptionJPA = foodOptionJPA;
        this.variousJPA = variousJPA;
    }

    RestaurantJPA restaurantJPA;
    FoodJPA foodJPA;
    FoodOptionJPA foodOptionJPA;
    VariousJPA variousJPA;

    @Transactional
    public void deleteAllRestaurants() {
        variousJPA.deleteAll();
        foodOptionJPA.deleteAll();
        foodJPA.deleteAll();
        restaurantJPA.deleteAll();
    }

    @Transactional
    public RestaurantEntity createRestaurant(Restaurant restaurant) {
        final RestaurantEntity entity = RestaurantEntity.fromModel(restaurant);
        final RestaurantEntity createdRestaurant = restaurantJPA.save(entity);

        createFoods(createdRestaurant);

        return createdRestaurant;
    }

    @Transactional
    public List<FoodEntity> createFoods(RestaurantEntity restaurant) {
        final List<FoodEntity> foods = restaurant.getFoods();

        foods.forEach(food -> food.setRestaurant(restaurant));
        final List<FoodEntity> createdFood = foodJPA.saveAll(foods);

        createdFood.forEach(this::createFoodOptions);

        return createdFood;
    }

    @Transactional
    public List<FoodOptionEntity> createFoodOptions(FoodEntity food) {
        final List<FoodOptionEntity> options = food.getOptions();
        options.forEach(option -> option.setFood(food));

        final List<FoodOptionEntity> createdOptions = foodOptionJPA.saveAll(options);
        createdOptions.forEach(this::createVarious);

        return createdOptions;
    }

    public List<VariousEntity> createVarious(FoodOptionEntity option) {
        final List<VariousEntity> various = option.getVarious();
        various.forEach(_various -> _various.setOption(option));

        return variousJPA.saveAll(various);
    }
}
