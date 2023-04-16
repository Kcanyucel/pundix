package com.pundix.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateRequest(

    @NotNull(message = "error.user.email.null")
    @NotBlank(message = "error.user.email.empty")
    String email,

    @NotNull(message = "error.user.password.null")
    @NotBlank(message = "error.user.password.empty")
    String password,
    String name, String surname) {
}

