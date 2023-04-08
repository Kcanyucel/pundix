package com.pundix.response.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserCreateResponse {

    private Long id;
    private String username;
    private String email;
    private LocalDateTime createdDate;

    public UserCreateResponse(Long id, String username, String email) {
        this(id, username, email, LocalDateTime.now());
    }
}
