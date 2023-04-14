package com.pundix.exception.custom;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class UserLoginFailedExpection extends BaseException implements Supplier<UserLoginFailedExpection> {

    public UserLoginFailedExpection() {
        super("error.user.login.failed", "UserLoginFailedExpection!", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UserLoginFailedExpection get() {
        return null;
    }
}
