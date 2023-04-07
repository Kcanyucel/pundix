package com.pundix.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserDeleteResponse {

    private String message;
    private String username;
    private LocalDateTime deletedDate;

    public UserDeleteResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}
