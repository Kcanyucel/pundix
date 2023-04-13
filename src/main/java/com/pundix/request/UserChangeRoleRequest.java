package com.pundix.request;

import com.pundix.entity.user.UserRole;

public class UserChangeRoleRequest {

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
