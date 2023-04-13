package com.pundix.response;

import java.time.LocalDateTime;

public class UserDeleteResponse {

    private final String message;
    private final String username;
    private final LocalDateTime deletedDate;

    private UserDeleteResponse(String message, String username, LocalDateTime deletedDate) {
        this.message = message;
        this.username = username;
        this.deletedDate = deletedDate;
    }

    public static UserDeleteResponse create(String message, String username, LocalDateTime deletedDate) {
        return new UserDeleteResponse(message, username, deletedDate);
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }
}
