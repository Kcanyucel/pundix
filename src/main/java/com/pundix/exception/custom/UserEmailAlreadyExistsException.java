package com.pundix.exception.custom;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class UserEmailAlreadyExistsException extends BaseException implements Supplier<UserEmailAlreadyExistsException> {

    public UserEmailAlreadyExistsException() {
        super("error.user.email.exist", "UserEmailAlreadyExistsException!", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserEmailAlreadyExistsException get() {
        return this;
    }
}