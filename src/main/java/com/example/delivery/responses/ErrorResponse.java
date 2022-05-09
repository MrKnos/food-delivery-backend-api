package com.example.delivery.responses;

public record ErrorResponse(String errorMessage) {

    public ErrorResponse(Exception exception) {
        this(exception.getMessage());
    }

}
