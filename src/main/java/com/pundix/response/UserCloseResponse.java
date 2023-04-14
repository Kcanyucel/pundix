package com.pundix.response;

import java.time.LocalDateTime;

public class UserCloseResponse {

    private final String message;
    private final LocalDateTime closedDate;

    private UserCloseResponse(String message, LocalDateTime closedDate) {
        this.message = message;
        this.closedDate = closedDate;
    }

    public static UserCloseResponse create(String message, LocalDateTime closedDate) {
        return new UserCloseResponse(message, closedDate);
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getClosedDate() {
        return closedDate;
    }
}

