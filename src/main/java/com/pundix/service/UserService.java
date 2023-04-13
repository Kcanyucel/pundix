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
import com.pundix.response.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    private final MessageResourceService messageResourceService;

    private final UserRepository userRepository;

    private final UserSessionInfoRepository userSessionInfoRepository;

    private final EntityMapperService entityMapperService;

    public UserService(MessageResourceService messageResourceService, UserRepository userRepository, UserSessionInfoRepository userSessionInfoRepository, EntityMapperService mapperService) {
        this.messageResourceService = messageResourceService;
        this.userRepository = userRepository;
        this.userSessionInfoRepository = userSessionInfoRepository;
        this.entityMapperService = mapperService;

    }

    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        User user = entityMapperService.createUser(userCreateRequest);
        userRepository.save(user);

        UserSessionInfo userSessionInfo = entityMapperService.createOrLoginUserSession(user.getId(), user.getUsername());
        userSessionInfoRepository.save(userSessionInfo);

        return UserCreateResponse.create(user.getId(), user.getUsername(), user.getEmail(), userSessionInfo.getAccessToken(), user.getCreatedDate());
    }

    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        User user = findUserByUsername(userLoginRequest.getUsername()).orElseThrow(new UserNotFoundException());

        UserSessionInfo userSessionInfo = entityMapperService.createOrLoginUserSession(user.getId(), user.getUsername());
        userSessionInfoRepository.save(userSessionInfo);

        return UserLoginResponse.create(messageResourceService.getMessage("user.is.login"), userSessionInfo.getLoginDate());
    }

    public UserLogoutResponse logoutUser(String accessToken) {
        UserSessionInfo userSessionInfo = findUserSessionInfoByAccessToken(accessToken).orElseThrow(UserSessionInfoNotFoundException::new);

        entityMapperService.logoutUserSession(userSessionInfo.getUserId(), userSessionInfo.getUsername());
        userSessionInfoRepository.save(userSessionInfo);

        return UserLogoutResponse.create(messageResourceService.getMessage("user.is.logout"), userSessionInfo.getUsername(), userSessionInfo.getLogoutDate());
    }

    public UserInfoResponse getUser(Long id) {
        User user = findUserById(id).orElseThrow(UserNotFoundException::new);

        return UserInfoResponse.create(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getSurname());
    }

    public UserDeleteResponse deleteUser(Long id) {
        User user = findUserById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
        userSessionInfoRepository.deleteUserSessionInfoByUserId(id);

        return UserDeleteResponse.create(messageResourceService.getMessage("user.is.deleted"), user.getUsername(), LocalDateTime.now());
    }

    public UserCloseResponse closeUser(Long id) {
        User user = findUserById(id).orElseThrow(UserNotFoundException::new);
        user.setUserStatus(UserStatus.CLOSED);

        userRepository.save(user);
        userSessionInfoRepository.closeUserSessionByUserId(user.getId());

        return UserCloseResponse.create(messageResourceService.getMessage("user.is.closed"), user.getUsername(), LocalDateTime.now());
    }

    public UserUpdateResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        findUserById(id).orElseThrow(UserNotFoundException::new);
        User user = entityMapperService.updateUser(userUpdateRequest);
        userRepository.save(user);

        UserSessionInfo userSessionInfo = entityMapperService.updateUserSession(user.getId(), user.getUsername());
        userSessionInfoRepository.save(userSessionInfo);

        return UserUpdateResponse.create(messageResourceService.getMessage("user.is.updated"), user.getUsername(), LocalDateTime.now());
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