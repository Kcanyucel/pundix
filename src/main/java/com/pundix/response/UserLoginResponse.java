package com.pundix.response;

import java.time.LocalDateTime;

public record UserLoginResponse(String message, LocalDateTime loginDate) {

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

