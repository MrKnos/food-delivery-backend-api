package com.example.delivery.exceptions;

import com.example.delivery.exceptions.data_not_found.DataNotFoundException;

public class UnknownTagException extends DataNotFoundException {

    public UnknownTagException(String tagName) {
        super(String.format("Unknown Tag %s", tagName));
    }

}