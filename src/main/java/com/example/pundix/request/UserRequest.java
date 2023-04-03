package com.example.pundix.request;

public record UserRequest(String username, String password, String email, String name, String surname) {

    public UserRequest(String email, String name, String surname) {
        this(null, null, email, name, surname);
    }
}
