package com.pundix.response;

import java.time.LocalDateTime;

public record UserCloseResponse(String message, LocalDateTime closedDate) {

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

