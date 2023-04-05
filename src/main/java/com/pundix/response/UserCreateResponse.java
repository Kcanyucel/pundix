package com.pundix.response;

import java.time.LocalDateTime;

public record UserCreateResponse(Long id, String username, String email, LocalDateTime createdDate) {

    public UserCreateResponse(Long id, String username, String email) {
        this(id, username, email, LocalDateTime.now());
    }
}
