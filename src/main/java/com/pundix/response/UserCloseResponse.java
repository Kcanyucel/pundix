package com.pundix.response;

import java.time.LocalDateTime;

public class UserCloseResponse {

    private final String message;
    private final String username;
    private final LocalDateTime closedDate;

    private UserCloseResponse(String message, String username, LocalDateTime closedDate) {
        this.message = message;
        this.username = username;
        this.closedDate = closedDate;
    }

    public static UserCloseResponse create(String message, String username, LocalDateTime closedDate) {
        return new UserCloseResponse(message, username, closedDate);
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }
}

