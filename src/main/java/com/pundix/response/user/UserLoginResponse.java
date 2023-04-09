package com.pundix.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponse {

    private String message;
    private String accessToken;
    private LocalDateTime loginDate;

    public UserLoginResponse(String message, String accessToken) {
        this(message, accessToken, LocalDateTime.now());
    }
}

