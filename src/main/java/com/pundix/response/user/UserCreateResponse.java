package com.pundix.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateResponse {

    private Long id;
    private String username;
    private String email;
    private String accessToken;
    private LocalDateTime createdDate;

    public UserCreateResponse(Long id, String username, String email, String accessToken) {
        this(id, username, email, accessToken, LocalDateTime.now());
    }
}
