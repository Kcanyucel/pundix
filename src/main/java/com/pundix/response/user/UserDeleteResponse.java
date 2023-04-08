package com.pundix.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserDeleteResponse {

    private String message;
    private String username;
    private LocalDateTime deletedDate;

    public UserDeleteResponse(String message, String username) {
        this(message, username, LocalDateTime.now());
    }
}
