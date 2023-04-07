package com.pundix.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserCloseResponse {

    private String message;
    private String username;
    private LocalDateTime closedDate;

    public UserCloseResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}

