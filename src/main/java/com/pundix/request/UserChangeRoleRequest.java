package com.pundix.request;

import com.pundix.entity.user.UserRole;

public record UserChangeRoleRequest(String username, UserRole userRole) {
}
