package com.example.pundix.response;

import java.time.LocalDateTime;

public record UserUpdateResponse(String message, String username, LocalDateTime updatedDate) {

    public UserUpdateResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}
