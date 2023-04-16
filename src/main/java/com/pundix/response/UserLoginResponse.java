package com.pundix.response;

import java.time.LocalDateTime;

public record UserLoginResponse(String message, LocalDateTime loginDate) {
}

