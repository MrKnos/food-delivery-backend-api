package com.example.delivery.entities;

import com.example.delivery.models.Restaurant;
import com.example.delivery.models.RestaurantType;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Getter
@Setter
@Table(name = "RESTAURANT")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "type")
    private RestaurantType type;

    @Nullable
    @Column(name = "rating_scroll")
    private Double ratingScroll;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<FoodEntity> foods;

    public static RestaurantEntity of(
            Long id,
            String name,
            Double latitude,
            Double longitude,
            RestaurantType type,
            Double ratingScroll,
            List<FoodEntity> foods
    ) {
        final RestaurantEntity entity = new RestaurantEntity();
        entity.setId(id);
        entity.setName(checkNotNull(name));
        entity.setLatitude(checkNotNull(latitude));
        entity.setLongitude(checkNotNull(longitude));
        entity.setType(checkNotNull(type));
        entity.setRatingScroll(ratingScroll);
        entity.setFoods(foods);

        return entity;
    }

    public static RestaurantEntity fromModel(Restaurant restaurant) {
        final RestaurantEntity entity = RestaurantEntity.of(
                null,
                checkNotNull(restaurant.name()),
                checkNotNull(restaurant.location().latitude()),
                checkNotNull(restaurant.location().longitude()),
                checkNotNull(restaurant.type()),
                restaurant.ratingScroll().orElse(null),
                checkNotNull(restaurant.foods())
                        .stream()
                        .map(FoodEntity::fromModel)
                        .collect(Collectors.toList())
        );

        entity.foods.forEach(food -> food.setRestaurant(entity));

        return entity;
    }
}