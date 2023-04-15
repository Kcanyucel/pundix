package com.pundix.request;

import com.pundix.entity.user.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserChangeRoleRequest {

    @NotNull(message = "error.user.username.null")
    @NotBlank(message = "error.user.username.empty")
    private String username;

    private UserRole userRole;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
