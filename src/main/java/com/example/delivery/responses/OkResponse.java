package com.example.delivery.responses;

public record OkResponse<T>(T result) {

   public static <T> OkResponse<T> of(T result) {
       return new OkResponse<>(result);
   }

}
