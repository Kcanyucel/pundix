package com.pundix.response;

import java.time.LocalDateTime;

public record UserCloseResponse(String message, LocalDateTime closedDate) {
}

