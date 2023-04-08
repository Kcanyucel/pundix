package com.pundix.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class UserUpdateResponse {

    private String message;
    private String username;
    LocalDateTime updatedDate;

    public UserUpdateResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}
