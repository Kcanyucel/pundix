package com.pundix.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLoginRequest(
    @NotNull(message = "error.user.username.null")
    @NotBlank(message = "error.user.username.empty")
    String username,

    @NotNull(message = "error.user.password.null")
    @NotBlank(message = "error.user.password.empty")
    String password) {
}
