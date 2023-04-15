package com.pundix.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserCreateRequest {

    @NotNull(message = "error.user.username.null")
    @NotBlank(message = "error.user.username.empty")
    private String username;

    @NotNull(message = "error.user.password.null")
    @NotBlank(message = "error.user.password.empty")
    private String password;

    @NotNull(message = "error.user.email.null")
    @NotBlank(message = "error.user.email.empty")
    private String email;
    private String name;
    private String surname;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
