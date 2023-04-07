package com.pundix.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserCreateRequest {

    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;

}
