package com.example.delivery.exceptions.data_not_found;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(Class<?> clazz, Long id) {
        super(String.format("Data %s with id %s not found", clazz.getSimpleName(), id));
    }

    public DataNotFoundException(Class<?> clazz, String value) {
        super(String.format("Data %s with value %s not found", clazz.getSimpleName(), value));
    }

}