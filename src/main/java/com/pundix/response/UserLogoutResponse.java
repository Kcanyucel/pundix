package com.pundix.response;

import java.time.LocalDateTime;

public class UserLogoutResponse {

    private final String message;
    private final LocalDateTime logoutDate;

    private UserLogoutResponse(String message, LocalDateTime logoutDate) {
        this.message = message;
        this.logoutDate = logoutDate;
    }

    public static UserLogoutResponse create(String message, LocalDateTime logoutDate) {
        return new UserLogoutResponse(message, logoutDate);
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }
}
