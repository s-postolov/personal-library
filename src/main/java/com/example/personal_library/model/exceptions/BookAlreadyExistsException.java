package com.example.personal_library.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyExistsException extends RuntimeException{
    public BookAlreadyExistsException(Long id) {
        super("The book with id %s already exists.".formatted(id));
    }
}