package com.example.delivery.exceptions;

public class UserNotFoundException extends DataNotFoundException {

    public UserNotFoundException(Long userId) {
        super(String.format("User id %s not found.", userId));
    }

    public UserNotFoundException(String name) {
        super(String.format("User name %s not found.", name));
    }

}
