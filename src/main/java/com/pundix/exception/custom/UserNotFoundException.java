package com.pundix.exception.custom;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("error.user.not.exist");
    }
}