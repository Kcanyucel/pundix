package com.pundix.response;

import java.time.LocalDateTime;

public record UserLogoutResponse(String message, LocalDateTime logoutDate) {

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
