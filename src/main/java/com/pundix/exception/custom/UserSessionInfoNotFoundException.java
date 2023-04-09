package com.pundix.exception.custom;

public class UserSessionInfoNotFoundException extends RuntimeException {

    public UserSessionInfoNotFoundException() {
        super("error.user.session.not.found");
    }
}


