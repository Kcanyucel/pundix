package com.pundix.response;

import java.time.LocalDateTime;

public class UserLoginResponse {

    private final String message;
    private final LocalDateTime loginDate;

    private UserLoginResponse(String message, LocalDateTime loginDate) {
        this.message = message;
        this.loginDate = loginDate;
    }

    public static UserLoginResponse create(String message, LocalDateTime loginDate) {
        return new UserLoginResponse(message, loginDate);
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }
}

