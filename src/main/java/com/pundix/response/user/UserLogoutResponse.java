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
public class UserLogoutResponse {

    private String message;
    private String username;
    private LocalDateTime logoutDate;

    public UserLogoutResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}
