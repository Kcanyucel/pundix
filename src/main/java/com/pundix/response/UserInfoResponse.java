package com.pundix.response;

public record UserInfoResponse(Long id, String username, String email,
                               String name, String surname) {

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



