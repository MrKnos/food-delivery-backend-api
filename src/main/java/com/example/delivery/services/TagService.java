package com.example.delivery.services;

import com.example.delivery.entities.RestaurantEntity;
import com.example.delivery.entities.TagEntity;
import com.example.delivery.exceptions.UnknownTagException;
import com.example.delivery.exceptions.data_not_found.RestaurantNotFoundException;
import com.example.delivery.models.RestaurantTag;
import com.example.delivery.reopositories.RestaurantRepository;
import com.example.delivery.reopositories.TagRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TagService {

    public TagService(
            RestaurantRepository restaurantRepository,
            TagRepository tagRepository
    ) {
        this.restaurantRepository = restaurantRepository;
        this.tagRepository = tagRepository;
    }

    RestaurantRepository restaurantRepository;
    TagRepository tagRepository;

    public ImmutableList<RestaurantTag> getTags() {
        return ImmutableList.copyOf(
                tagRepository.findAll()
                        .stream().map(RestaurantTag::fromEntity)
                        .toList()
        );
    }

    @Transactional
    public void addRestaurantTag(Long restaurantId, RestaurantTag tag) {
        final Optional<TagEntity> _tag = tagRepository.existsByName(tag.name())
                ? tagRepository.findByName(tag.name())
                : Optional.of(tagRepository.save(TagEntity.of(tag.name())));

        final RestaurantEntity restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));

        restaurant.addTag(_tag.orElseThrow());
        restaurantRepository.save(restaurant);
    }

   public RestaurantTag getTagFromString(String name) {
        try {
            return RestaurantTag.valueOf(name.toUpperCase());
        } catch (Exception exception) {
            throw new UnknownTagException(name);
        }
    }
}
