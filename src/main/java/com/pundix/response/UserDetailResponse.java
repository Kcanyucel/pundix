package com.pundix.response;

public record UserDetailResponse(String username, String email, String name, String surname) {

    public UserDetailResponse(String username, String email, String name, String surname) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }
}



