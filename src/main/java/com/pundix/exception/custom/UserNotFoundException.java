package com.pundix.exception.custom;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class UserNotFoundException extends BaseException implements Supplier<UserNotFoundException> {

    public UserNotFoundException() {
        super("error.user.not.exist", "UserNotFoundException!", HttpStatus.BAD_REQUEST);
    }


    @Override
    public UserNotFoundException get() {
        return this;
    }
}