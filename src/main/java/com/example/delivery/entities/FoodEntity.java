package com.example.delivery.entities;

import com.example.delivery.models.Food;
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
@Table(name = "FOOD")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Nullable
    @Column(name = "originalPrice")
    private Double originalPrice;

    @OneToMany(mappedBy = "food")
    private List<FoodOptionEntity> options;

    public static FoodEntity fromModel(Food food) {
        final FoodEntity entity = new FoodEntity();

        entity.setName(checkNotNull(food.name()));
        entity.setDescription(food.description());
        entity.setPrice(checkNotNull(food.price()));
        entity.setOriginalPrice(food.originalPrice().orElse(null));
        entity.setOptions(
                checkNotNull(food.options())
                        .stream()
                        .map(FoodOptionEntity::fromModel)
                        .collect(Collectors.toList())
        );

        return entity;
    }
}
