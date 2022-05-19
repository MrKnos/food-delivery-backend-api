package com.example.delivery.exceptions;

public class TagNotFoundException extends DataNotFoundException {

    public TagNotFoundException(String tagName) {
        super(String.format("Tag %s not found.", tagName));
    }

}
