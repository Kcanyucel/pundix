package com.pundix.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserUpdateResponse {

    private String message;
    private String username;
    LocalDateTime updatedDate;

    public UserUpdateResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}
