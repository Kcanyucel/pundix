package com.pundix.response;

import java.time.LocalDateTime;

public class UserUpdateResponse {

    private final String message;
    private final String username;
    private final LocalDateTime updatedDate;

    private UserUpdateResponse(String message, String username, LocalDateTime updatedDate) {
        this.message = message;
        this.username = username;
        this.updatedDate = updatedDate;
    }

    public static UserUpdateResponse create(String message, String username, LocalDateTime updatedDate) {
        return new UserUpdateResponse(message, username, updatedDate);
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
