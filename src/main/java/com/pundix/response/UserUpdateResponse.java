package com.pundix.response;

import java.time.LocalDateTime;

public record UserUpdateResponse(String message, String username,
                                 LocalDateTime updatedDate) {

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
