package com.pundix.response;

import com.pundix.entity.user.UserRole;

public record UserChangeRoleResponse(String message, String username,
                                     UserRole userRole) {

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
