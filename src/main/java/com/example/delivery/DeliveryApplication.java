package com.example.delivery;

import com.example.delivery.services.RestaurantService;
import com.example.delivery.services.TagService;
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
            TagService tagService,
            RestaurantService restaurantService
    ) {
        return (args) -> {
//            restaurantService.deleteAllRestaurants();
//            final Restaurant restaurant = restaurantService.createRestaurantFromForm(RestaurantForm.fromMock());
//            tagService.addRestaurantTag(restaurant.id().orElse(0L), RestaurantTag.STREET_FOOD);
        };
    }

}
