package com.pundix.response;

import java.time.LocalDateTime;

public record UserUpdateResponse(String message, String username,
                                 LocalDateTime updatedDate) {
}
