package com.bookstore.bookstore.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsernameNotFoundException extends RuntimeException{

    public UsernameNotFoundException(String username) {
        super(String.format("Username with username: %s was not found", username));
    }
}
