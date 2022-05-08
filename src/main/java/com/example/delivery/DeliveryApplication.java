package com.example.delivery;

import com.example.delivery.models.Restaurant;
import com.example.delivery.reopositories.RestaurantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);
    }

    @Bean
    public CommandLineRunner initial(
            RestaurantRepository restaurantRepository
    ) {
        return (args) -> {
            restaurantRepository.deleteAllRestaurants();
            restaurantRepository.createRestaurant(Restaurant.fromMock());
        };
    }

}
