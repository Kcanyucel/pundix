package com.pundix.exception.custom;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class UserAlreadyExistsException extends BaseException implements Supplier<UserAlreadyExistsException> {

    public UserAlreadyExistsException() {
        super("error.user.exist", "UserAlreadyExistsException!", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserAlreadyExistsException get() {
        return this;
    }
}