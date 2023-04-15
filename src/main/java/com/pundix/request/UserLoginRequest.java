package com.pundix.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserLoginRequest {

    @NotNull(message = "error.user.username.null")
    @NotBlank(message = "error.user.username.empty")
    private String username;

    @NotNull(message = "error.user.password.null")
    @NotBlank(message = "error.user.password.empty")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
