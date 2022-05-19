package com.example.delivery.controllers;

import com.example.delivery.ConstantMessages;
import com.example.delivery.models.RestaurantTag;
import com.example.delivery.requests.AddRestaurantTagRequest;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.TagService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    TagService tagService;

    @PostMapping("/restaurants/{id}/tags")
    public OkResponse<String> addRestaurantTag(
            @PathVariable Long id,
            @RequestBody AddRestaurantTagRequest request
    ) {
        final RestaurantTag _tag = tagService.getTagFromString(request.tag());
        tagService.addRestaurantTag(id, _tag);

        return  OkResponse.of(ConstantMessages.SUCCESS);
    }
}
