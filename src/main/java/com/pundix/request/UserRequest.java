package com.pundix.request;

public record UserRequest(String username, String password, String email, String name, String surname) {

    public UserRequest(String username, String password, String email, String name, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public UserRequest(String email, String name, String surname) {
        this(null, null, email, name, surname);
    }
}
