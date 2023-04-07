package com.pundix.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserUpdateRequest {

    private String email;
    private String password;
    private String name;
    private String surname;

}
