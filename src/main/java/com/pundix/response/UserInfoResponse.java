package com.pundix.response;

public class UserInfoResponse {

    private final Long id;
    private final String username;
    private final String email;
    private final String name;
    private final String surname;

    private UserInfoResponse(Long id, String username, String email, String name, String surname) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public static UserInfoResponse create(Long id, String username, String email, String name, String surname) {
        return new UserInfoResponse(id, username, email, name, surname);
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

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}



