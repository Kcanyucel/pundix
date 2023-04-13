package com.pundix.service;

import com.pundix.entity.builder.UserBuilder;
import com.pundix.entity.builder.UserSessionInfoBuilder;
import com.pundix.entity.user.User;
import com.pundix.entity.user.UserRole;
import com.pundix.entity.user.UserSessionInfo;
import com.pundix.entity.user.UserStatus;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntityMapperService {

    private final PasswordEncoderService passwordEncoderService;

    private final TokenService tokenService;

    public EntityMapperService(PasswordEncoderService passwordEncoderService, TokenService tokenService) {
        this.passwordEncoderService = passwordEncoderService;
        this.tokenService = tokenService;
    }

    public User createUser(UserCreateRequest userCreateRequest) {
        return new UserBuilder()
            .username(userCreateRequest.getUsername().toLowerCase())
            .password(passwordEncoderService.encodePassword(userCreateRequest.getPassword()))
            .email(userCreateRequest.getEmail().toLowerCase())
            .name(userCreateRequest.getName())
            .surname(userCreateRequest.getSurname())
            .userRole(UserRole.COSTUMER)
            .createdDate(LocalDateTime.now())
            .userStatus(UserStatus.ACTIVE)
            .build();
    }

    public UserSessionInfo createUserSession(Long id, String username) {
        return new UserSessionInfoBuilder()
            .userId(id)
            .username(username)
            .accessToken(tokenService.createAccessToken())
            .loginDate(LocalDateTime.now())
            .build();
    }

    public void updateUser(User user, UserUpdateRequest userUpdateRequest) {
        user.setEmail(userUpdateRequest.getEmail().toLowerCase());
        user.setPassword(passwordEncoderService.encodePassword(userUpdateRequest.getPassword()));
        user.setName(userUpdateRequest.getName());
        user.setSurname(userUpdateRequest.getSurname());

    }

    public void closeUserSession(UserSessionInfo userSessionInfo) {



    }
}
