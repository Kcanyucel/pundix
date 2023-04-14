package com.pundix.exception.custom;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class UserSessionInfoNotFoundException extends BaseException implements Supplier<UserSessionInfoNotFoundException> {

    public UserSessionInfoNotFoundException() {
        super("error.user.session.not.found", "UserSessionInfoNotFoundException!", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserSessionInfoNotFoundException get() {
        return this;
    }
}


