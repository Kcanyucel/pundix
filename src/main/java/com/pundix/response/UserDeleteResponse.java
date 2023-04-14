package com.pundix.response;

import java.time.LocalDateTime;

public class UserDeleteResponse {

    private final String message;
    private final LocalDateTime deletedDate;

    private UserDeleteResponse(String message, LocalDateTime deletedDate) {
        this.message = message;
        this.deletedDate = deletedDate;
    }

    public static UserDeleteResponse create(String message, LocalDateTime deletedDate) {
        return new UserDeleteResponse(message, deletedDate);
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDeletedDate() {
        return deletedDate;
    }
}
