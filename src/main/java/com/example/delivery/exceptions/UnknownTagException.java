package com.example.delivery.exceptions;

public class UnknownTagException extends DataNotFoundException {

    public UnknownTagException(String tagName) {
        super(String.format("Unknown Tag %s", tagName));
    }

}