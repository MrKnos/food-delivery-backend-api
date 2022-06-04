package com.example.delivery.controllers;

import com.example.delivery.exceptions.DataNotFoundException;
import com.example.delivery.exceptions.UnknownTagException;
import com.example.delivery.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> dataNotFoundHandler(DataNotFoundException exception) {
        return new ResponseEntity<>(
                new ErrorResponse(exception),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UnknownTagException.class)
    public ResponseEntity<ErrorResponse> unknownTagHandler(UnknownTagException exception) {
        return new ResponseEntity<>(
                new ErrorResponse(exception),
                HttpStatus.NOT_FOUND
        );
    }

}
