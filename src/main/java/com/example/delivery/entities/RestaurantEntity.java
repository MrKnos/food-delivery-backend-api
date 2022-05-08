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

    @OneToMany(mappedBy = "restaurant")
    private List<FoodEntity> foods;

    public static RestaurantEntity fromModel(Restaurant restaurant) {
        final RestaurantEntity entity = new RestaurantEntity();

        entity.setName(checkNotNull(restaurant.name()));
        entity.setLatitude(checkNotNull(restaurant.location().latitude()));
        entity.setLongitude(checkNotNull(restaurant.location().longitude()));
        entity.setType(checkNotNull(restaurant.type()));
        entity.setRatingScroll(restaurant.ratingScroll());
        entity.setFoods(
                checkNotNull(restaurant.foods())
                        .stream()
                        .map(FoodEntity::fromModel)
                        .collect(Collectors.toList())
        );

        return entity;
    }
}