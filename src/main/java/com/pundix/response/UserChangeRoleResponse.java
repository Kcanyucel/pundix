package com.pundix.response;

import com.pundix.entity.user.UserRole;

public class UserChangeRoleResponse {

    private final String message;
    private final String username;
    private final UserRole userRole;

    private UserChangeRoleResponse(String message, String username, UserRole userRole) {
        this.message = message;
        this.username = username;
        this.userRole = userRole;
    }

    public static UserChangeRoleResponse create(String message, String username, UserRole userRole) {
        return new UserChangeRoleResponse(message, username, userRole);
    }

    public String getMessage() {
        return message;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getUserRole() {
        return userRole;
    }
}
