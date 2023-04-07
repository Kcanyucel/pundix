package com.pundix.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDetailResponse {

    private Long id;
    private String username;
    private String email;
    private String name;
    private String surname;
}



