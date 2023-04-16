package com.pundix.response;

import java.time.LocalDateTime;

public record UserCreateResponse(Long id, String username, String email, String accessToken,
                                 LocalDateTime createdDate) {
}
