package com.example.delivery.services;

import com.example.delivery.entities.FoodEntity;
import com.example.delivery.entities.OfficeHoursEntity;
import com.example.delivery.entities.RestaurantEntity;
import com.example.delivery.exceptions.DataNotFoundException;
import com.example.delivery.forms.RestaurantForm;
import com.example.delivery.models.Food;
import com.example.delivery.models.OfficeHours;
import com.example.delivery.models.Restaurant;
import com.example.delivery.models.RestaurantPredicate;
import com.example.delivery.reopositories.*;
import com.example.delivery.requests.RestaurantPredicateRequest;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@Service
public class RestaurantService {

    public RestaurantService(
            RestaurantRepository restaurantRepository,
            FoodRepository foodRepository,
            FoodOptionRepository foodOptionRepository,
            VariousRepository variousRepository,
            OfficeHoursRepository officeHoursRepository
    ) {
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.foodOptionRepository = foodOptionRepository;
        this.variousRepository = variousRepository;
        this.officeHoursRepository = officeHoursRepository;
    }

    RestaurantRepository restaurantRepository;
    FoodRepository foodRepository;
    FoodOptionRepository foodOptionRepository;
    VariousRepository variousRepository;
    OfficeHoursRepository officeHoursRepository;

    public ImmutableList<Restaurant> getRestaurants() {
        return ImmutableList.copyOf(
                restaurantRepository.findAll()
                        .stream()
                        .map(Restaurant::fromEntity)
                        .toList()
        );
    }

    RestaurantEntity getRestaurantEntityById(Long id) {
        return restaurantRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException(RestaurantEntity.class, id));
    }

    public ImmutableList<Restaurant> filterRestaurants(
            RestaurantPredicateRequest predicate
    ) {
        final RestaurantPredicate _predicate = RestaurantPredicate.fromRequest(predicate);

        return ImmutableList.copyOf(
                getRestaurants()
                        .stream().filter(restaurant -> restaurant.predicate(_predicate))
                        .toList()
        );
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository
                .findById(id)
                .map(Restaurant::fromEntity)
                .orElseThrow(() -> new DataNotFoundException(RestaurantEntity.class, id));
    }

    public ImmutableList<Food> listFoodsInRestaurant(Long restaurantId) {
        return ImmutableList.copyOf(
                getRestaurantById(restaurantId).foods()
        );
    }

    public void addFoods(
            Long restaurantId,
            ImmutableList<Food> foods
    ) {
        final RestaurantEntity restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new DataNotFoundException(RestaurantEntity.class, restaurantId));

        final List<FoodEntity> foodEntities = foods
                .stream().map(FoodEntity::fromModel)
                .toList();

        foodEntities.forEach(food -> food.setRestaurant(restaurant));

        foodRepository.saveAll(foodEntities);
    }

    public void addOfficeHours(
            Long restaurantId,
            ImmutableList<OfficeHours> officeHours
    ) {
        final RestaurantEntity restaurant = getRestaurantEntityById(restaurantId);
        final List<OfficeHoursEntity> entities = officeHours
                .stream().map(OfficeHoursEntity::fromModel)
                .toList();

        entities.forEach(entity -> entity.setRestaurant(restaurant));
        officeHoursRepository.saveAll(entities);
    }

    public void updateOfficeHours(
            Long restaurantId,
            OfficeHours officeHours
    ) {
        final OfficeHoursEntity entity = officeHoursRepository.findByRestaurantIdAndDay(
                restaurantId,
                officeHours.day().name()
        ).orElseThrow(() -> new DataNotFoundException(
                OfficeHoursEntity.class, officeHours.day().name())
        );

        entity.setOpen(officeHours.open());
        entity.setClose(officeHours.close());

        officeHoursRepository.save(entity);
    }

    public void deleteAllRestaurants() {
        restaurantRepository.deleteAll();
    }

    public void deleteRestaurantById(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new DataNotFoundException(RestaurantEntity.class, id);
        }

        restaurantRepository.deleteById(id);
    }

    @Transactional
    public void deleteFoodsInRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) {
            throw new DataNotFoundException(RestaurantEntity.class, id);

        }

        foodRepository.deleteByRestaurantId(id);

    }

    @Transactional
    public void deleteRestaurantOfficeHour(Long restaurantId, DayOfWeek day) {
        if (!officeHoursRepository.existsByRestaurantIdAndDay(restaurantId, day.name())) {
            throw new DataNotFoundException(OfficeHoursEntity.class, day.name());
        }

        officeHoursRepository.deleteByRestaurantIdAndDay(restaurantId, day.name());
    }

    public Restaurant createRestaurantFromForm(RestaurantForm form) {
        final RestaurantEntity createdRestaurant = restaurantRepository.save(
                RestaurantEntity.fromForm(form)
        );

        return Restaurant.fromEntity(createdRestaurant);
    }
}
