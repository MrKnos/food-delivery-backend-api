package com.example.delivery;

import com.example.delivery.models.Restaurant;
import com.example.delivery.services.RestaurantService;
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
            RestaurantService restaurantService
    ) {
        return (args) -> {
            restaurantService.deleteAllRestaurants();
            restaurantService.createRestaurant(Restaurant.fromMock());
        };
    }

}
