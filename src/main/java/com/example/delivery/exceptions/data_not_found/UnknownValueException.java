package com.example.delivery.exceptions.data_not_found;

import static java.lang.String.format;

public class UnknownValueException  extends RuntimeException {

    public UnknownValueException(Class<?> clazz, String message) {
        super(format("Value %s with %s unknown", clazz, message));
    }

}
