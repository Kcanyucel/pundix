package com.pundix.response;

import java.time.LocalDateTime;

public record UserLogoutResponse(String message, LocalDateTime logoutDate) {
}
