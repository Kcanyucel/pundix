package com.example.pundix.response;

import java.time.LocalDateTime;

public record UserDeleteResponse(String message, String username, LocalDateTime deletedDate) {

    public UserDeleteResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}
