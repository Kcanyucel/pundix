package com.pundix.response;

import java.time.LocalDateTime;

public record UserDeleteResponse(String message, LocalDateTime deletedDate) {

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
