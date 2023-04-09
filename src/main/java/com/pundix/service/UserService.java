package com.pundix.service;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserSessionInfo;
import com.pundix.entity.user.UserStatus;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.exception.custom.UserSessionInfoNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.repository.UserSessionInfoRepository;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.user.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final TokenService tokenService;

    private final PasswordEncoderService passwordEncoderService;

    private final MessageResourceService messageResourceService;

    private final UserRepository userRepository;

    private final UserSessionInfoRepository userSessionInfoRepository;

    public UserService(PasswordEncoderService passwordEncoderService, MessageResourceService messageResourceService, TokenService tokenService, UserSessionInfoRepository userSessionInfoRepository, UserRepository userRepository) {
        this.passwordEncoderService = passwordEncoderService;
        this.messageResourceService = messageResourceService;
        this.userRepository = userRepository;
        this.userSessionInfoRepository = userSessionInfoRepository;
        this.tokenService = tokenService;
    }

    public UserCreateResponse createUser(UserCreateRequest userRequest) {
        String encodedpassword = passwordEncoderService.encodePassword(userRequest.getPassword());

        User user = User.builder()
            .username(userRequest.getUsername().toLowerCase())
            .password(encodedpassword)
            .email(userRequest.getEmail().toLowerCase())
            .name(userRequest.getName())
            .surname(userRequest.getSurname())
            .createdDate(LocalDateTime.now())
            .userStatus(UserStatus.ACTIVE)
            .build();
        userRepository.save(user);

        UserSessionInfo userSessionInfo = UserSessionInfo.builder()
            .userId(user.getId())
            .username(user.getUsername().toLowerCase())
            .accessToken(tokenService.createAccessToken())
            .loginDate(LocalDateTime.now())
            .build();
        userSessionInfoRepository.save(userSessionInfo);

        return new UserCreateResponse(user.getId(), user.getUsername(), user.getEmail(), userSessionInfo.getAccessToken());
    }

    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        Optional<User> user = findUserByUsername(userLoginRequest.getUsername());

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        UserSessionInfo userSessionInfo = UserSessionInfo.builder()
            .userId(user.get().getId())
            .username(user.get().getUsername().toLowerCase())
            .accessToken(tokenService.createAccessToken())
            .loginDate(LocalDateTime.now())
            .build();
        userSessionInfoRepository.save(userSessionInfo);

        return new UserLoginResponse(messageResourceService.getMessage("user.is.login"), userSessionInfo.getAccessToken());
    }

    public UserLogoutResponse logoutUser(String accessToken) {
        Optional<UserSessionInfo> sessionInfo = findUserSessionInfoByAccessToken(accessToken);

        if (sessionInfo.isEmpty()) {
            throw new UserSessionInfoNotFoundException();
        }
        UserSessionInfo userSessionInfoAfterLogout = UserSessionInfo.builder()
            .loginDate(sessionInfo.get().getLoginDate())
            .id(sessionInfo.get().getId())
            .userId(sessionInfo.get().getUserId())
            .username(sessionInfo.get().getUsername())
            .logoutDate(LocalDateTime.now())
            .accessToken(null)
            .build();
        userSessionInfoRepository.save(userSessionInfoAfterLogout);

        return new UserLogoutResponse(messageResourceService.getMessage("user.is.logout"), sessionInfo.get().getUsername());
    }


    public UserInfoResponse getUser(Long id) {
        Optional<User> user = findUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User foundUser = user.get();

        return new UserInfoResponse(foundUser.getId(), foundUser.getUsername(), foundUser.getEmail(), foundUser.getName(), foundUser.getSurname());
    }

    public UserDeleteResponse deleteUser(Long id) {
        Optional<User> user = findUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User userToBeDeleted = user.get();
        userRepository.deleteById(id);
        userSessionInfoRepository.deleteUserSessionInfoByUserId(user.get().getId());

        return new UserDeleteResponse(messageResourceService.getMessage("user.is.deleted"), userToBeDeleted.getUsername());
    }

    public UserCloseResponse closeUser(Long id) {
        Optional<User> user = findUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User closedUser = user.get();
        closedUser.setUserStatus(UserStatus.CLOSED);
        userRepository.save(closedUser);
        userSessionInfoRepository.closeSessionByUserId(closedUser.getId());

        return new UserCloseResponse(messageResourceService.getMessage("user.is.closed"), closedUser.getUsername());
    }

    public UserUpdateResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        String encodedpassword = passwordEncoderService.encodePassword(userUpdateRequest.getPassword());
        Optional<User> user = findUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User updatedUser = User.builder()
            .username(user.get().getUsername())
            .password(encodedpassword)
            .email(userUpdateRequest.getEmail())
            .name(userUpdateRequest.getName())
            .surname(userUpdateRequest.getSurname()).build();
        userRepository.save(updatedUser);

        return new UserUpdateResponse(messageResourceService.getMessage("user.is.updated"), updatedUser.getUsername());
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public Optional<UserSessionInfo> findUserSessionInfoByAccessToken(String accessToken) {
        return userSessionInfoRepository.findUserSessionInfoByAccessToken(accessToken);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existByPassword(String password) {
        return userRepository.existsByPassword(password);
    }
}