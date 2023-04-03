package com.example.pundix.response;

import java.time.LocalDateTime;

public record UserCloseResponse(String message, String username, LocalDateTime closedDate) {

    public UserCloseResponse(String message, String username) {

        this(message, username, LocalDateTime.now());
    }
}

