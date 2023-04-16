package com.pundix.response;

import java.time.LocalDateTime;

public record UserDeleteResponse(String message, LocalDateTime deletedDate) {
}
