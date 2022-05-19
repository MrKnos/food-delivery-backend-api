package com.example.delivery.presenters;

import com.example.delivery.models.RestaurantTag;
import com.google.common.collect.ImmutableList;

import java.util.stream.Collectors;

public record GetTagsPresenter(ImmutableList<String> tags) {

  public static GetTagsPresenter from(ImmutableList<RestaurantTag> tags) {
      return new GetTagsPresenter(
              ImmutableList.copyOf(
                      tags.stream().map(RestaurantTag::name)
                              .collect(Collectors.toList())
              )
      );
  }

}
