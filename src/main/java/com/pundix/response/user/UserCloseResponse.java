package com.pundix.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserCloseResponse {

    private String message;
    private String username;
    private LocalDateTime closedDate;

    public UserCloseResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}

