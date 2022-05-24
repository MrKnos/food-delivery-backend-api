package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.forms.RestaurantForm;
import com.example.delivery.forms.RestaurantOfficeHoursForm;
import com.example.delivery.models.Food;
import com.example.delivery.models.OfficeHours;
import com.example.delivery.models.Restaurant;
import com.example.delivery.models.RestaurantTag;
import com.example.delivery.requests.AddFoodsRequest;
import com.example.delivery.requests.AddOfficeHoursRequest;
import com.example.delivery.requests.AddRestaurantTagRequest;
import com.example.delivery.requests.RestaurantPredicateRequest;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.RestaurantService;
import com.example.delivery.services.TagService;
import com.google.common.collect.ImmutableList;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    public RestaurantController(
            RestaurantService restaurantService,
            TagService tagService
    ) {
        this.restaurantService = restaurantService;
        this.tagService = tagService;
    }

    RestaurantService restaurantService;
    TagService tagService;

    @GetMapping
    public OkResponse<ImmutableList<Restaurant>> getRestaurants() {
        return OkResponse.of(restaurantService.getRestaurants());
    }

    @GetMapping("/{id}")
    public OkResponse<Restaurant> getRestaurant(@PathVariable Long id) {
        return OkResponse.of(restaurantService.getRestaurantById(id));
    }

    @GetMapping("/{id}/foods")
    public OkResponse<ImmutableList<Food>> getFoods(@PathVariable Long id) {
        return OkResponse.of(restaurantService.listFoodsInRestaurant(id));
    }

    @PostMapping
    public OkResponse<Restaurant> createRestaurant(@RequestBody RestaurantForm form) {
        return OkResponse.of(restaurantService.createRestaurantFromForm(form));
    }

    @PostMapping("/search")
    public OkResponse<ImmutableList<Restaurant>> filterRestaurants(
            @RequestBody RestaurantPredicateRequest predicate
    ) {
        return OkResponse.of(restaurantService.filterRestaurants(predicate));
    }

    @PostMapping("/{id}/tags")
    public OkResponse<String> addRestaurantTag(
            @PathVariable Long id,
            @RequestBody AddRestaurantTagRequest request
    ) {
        final RestaurantTag _tag = tagService.getTagFromString(request.tag());
        tagService.addRestaurantTag(id, _tag);

        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @PostMapping("/{id}/foods")
    public OkResponse<String> addFoods(
            @PathVariable Long id,
            @RequestBody AddFoodsRequest request
    ) {
        restaurantService.addFoods(
                id,
                ImmutableList.copyOf(
                        request.foods().stream().map(Food::fromForm).toList()
                )
        );

        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @PostMapping("/{id}/office_hours")
    public OkResponse<String> addOfficeHours(
            @PathVariable Long id,
            @RequestBody AddOfficeHoursRequest request
    ) {
        restaurantService.addOfficeHours(
                id,
                ImmutableList.copyOf(request.officeHours())
        );

        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @PutMapping("/{id}/office_hours")
    public OkResponse<String> updateOfficeHours(
            @PathVariable Long id,
            @RequestBody RestaurantOfficeHoursForm form
    ) {
        restaurantService.updateOfficeHours(id, OfficeHours.fromForm(form));
        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @DeleteMapping("/{id}")
    public OkResponse<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurantById(id);
        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @DeleteMapping("/{id}/foods")
    public OkResponse<String> deleteFoodsInRestaurant(@PathVariable Long id) {
        restaurantService.deleteFoodsInRestaurant(id);
        return OkResponse.of(ConstantMessages.SUCCESS);
    }

    @DeleteMapping("/{id}/office_hours/{day}")
    public OkResponse<String> deleteRestaurantOfficeHours(
            @PathVariable Long id,
            @PathVariable String day
    ) {
        restaurantService.deleteRestaurantOfficeHour(id, DayOfWeek.valueOf(day.toUpperCase()));
        return OkResponse.of(ConstantMessages.SUCCESS);
    }
}
