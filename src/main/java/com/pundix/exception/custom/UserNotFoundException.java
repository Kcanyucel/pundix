package com.pundix.exception.custom;

import java.util.function.Supplier;

public class UserNotFoundException extends RuntimeException implements Supplier<UserNotFoundException> {

    public UserNotFoundException() {
        super("error.user.not.exist");
    }


    @Override
    public UserNotFoundException get() {
        return this;
    }
}