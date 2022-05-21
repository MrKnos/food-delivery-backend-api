package com.example.delivery.controllers;

import com.example.delivery.presenters.GetTagsPresenter;
import com.example.delivery.responses.OkResponse;
import com.example.delivery.services.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TagController {
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    TagService tagService;

    @GetMapping("/tags")
    public OkResponse<GetTagsPresenter> getTags() {
        return OkResponse.of(
                GetTagsPresenter.from(tagService.getTags())
        );
    }
}
