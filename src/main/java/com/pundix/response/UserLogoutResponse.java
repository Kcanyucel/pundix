package com.pundix.response;

import java.time.LocalDateTime;
public class UserLogoutResponse {

    private final String message;
    private final String username;
    private final LocalDateTime logoutDate;

    private UserLogoutResponse(String message, String username, LocalDateTime logoutDate) {
        this.message = message;
        this.username = username;
        this.logoutDate = logoutDate;
    }

    public static UserLogoutResponse create(String message, String username, LocalDateTime logoutDate) {
        return new UserLogoutResponse(message, username, logoutDate);
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }
}
