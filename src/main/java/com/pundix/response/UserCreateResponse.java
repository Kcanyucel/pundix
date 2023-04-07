package com.pundix.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class UserCreateResponse {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdDate;

    public UserCreateResponse(Long id, String username, String email) {
        this(id, username, email, LocalDateTime.now());
    }
}
