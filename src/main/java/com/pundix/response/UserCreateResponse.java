package com.pundix.response;

import java.time.LocalDateTime;

public class UserCreateResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final String accessToken;
    private final LocalDateTime createdDate;

    private UserCreateResponse(Long id, String username, String email, String accessToken, LocalDateTime createdDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.accessToken = accessToken;
        this.createdDate = createdDate;
    }

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
