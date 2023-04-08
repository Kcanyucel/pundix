package com.pundix.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginResponse {

    private String message;
    private LocalDateTime loginDate;

    public UserLoginResponse(String message) {
        this(message, LocalDateTime.now());
    }
}

