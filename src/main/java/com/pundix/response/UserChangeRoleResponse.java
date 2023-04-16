package com.pundix.response;

import com.pundix.entity.user.UserRole;

public record UserChangeRoleResponse(String message, String username,
                                     UserRole userRole) {
}
