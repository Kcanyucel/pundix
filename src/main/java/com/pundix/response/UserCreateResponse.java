package com.pundix.response;

import java.time.LocalDateTime;

public record UserCreateResponse(Long id, String username, String email,
                                 String accessToken, LocalDateTime createdDate) {

    public static UserCreateResponse create(Long id, String username, String email, String accessToken, LocalDateTime createdDate) {
        return new UserCreateResponse(id, username, email, accessToken, createdDate);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
