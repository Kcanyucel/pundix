package com.example.pundix.response;

public record UserDetailResponse(Long id, String username, String email, String name, String surname) {

    public UserDetailResponse(String username, String email, String name, String surname) {
        this(null, username, email, name, surname);
    }
}
