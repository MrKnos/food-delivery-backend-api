package com.example.delivery.entities;

import com.example.delivery.forms.RestaurantForm;
import com.example.delivery.models.RestaurantTag;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Column(name = "tag")
    private RestaurantTag tag;

    @Nullable
    @Column(name = "rating_scroll")
    private Double ratingScroll;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<FoodEntity> foods = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "restaurant_tag",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")

    )
    private Set<TagEntity> tags = new HashSet<>();

    public static RestaurantEntity of(
            Long id,
            String name,
            Double latitude,
            Double longitude,
            Double ratingScroll
    ) {
        final RestaurantEntity entity = new RestaurantEntity();
        entity.setId(id);
        entity.setName(name);
        entity.setLatitude(latitude);
        entity.setLongitude(longitude);
        entity.setRatingScroll(ratingScroll);

        return entity;
    }

    public static RestaurantEntity fromForm(RestaurantForm form) {
        return RestaurantEntity.of(
                null,
                checkNotNull(form.name()),
                checkNotNull(form.location().latitude()),
                checkNotNull(form.location().longitude()),
                null
        );
    }

    public void addTag(TagEntity tag) {
        tags.add(tag);
        tag.getRestaurants().add(this);
    }

    public void addFood(FoodEntity food) {
        foods.add(food);
        food.setRestaurant(this);
    }
}