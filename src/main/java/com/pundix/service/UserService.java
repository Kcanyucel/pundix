package com.pundix.service;

import com.pundix.entity.User;
import com.pundix.entity.UserStatus;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoderService passwordEncoderService;

    private final MessageResourceService messageResourceService;

    private final UserRepository userRepository;

    public UserService(PasswordEncoderService passwordEncoderService, MessageResourceService messageResourceService, UserRepository userRepository) {
        this.passwordEncoderService = passwordEncoderService;
        this.messageResourceService = messageResourceService;
        this.userRepository = userRepository;
    }

    public UserCreateResponse createUser(UserCreateRequest userRequest) {
        String encodingPassword = passwordEncoderService.encodePassword(userRequest.getPassword());

        User user = User.builder()
            .username(userRequest.getUsername().toLowerCase())
            .password(encodingPassword)
            .email(userRequest.getEmail().toLowerCase())
            .name(userRequest.getName())
            .surname(userRequest.getSurname()).build();

        userRepository.save(user);

        return new UserCreateResponse(user.getId(), user.getUsername(), user.getEmail());
    }

    public UserDetailResponse getUser(Long id) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User foundUser = user.get();

        return new UserDetailResponse(foundUser.getId(), foundUser.getUsername(), foundUser.getEmail(), foundUser.getName(), foundUser.getSurname());
    }

    public UserDeleteResponse deleteUser(Long id) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User userToBeDeleted = user.get();
        userRepository.deleteById(id);
        return new UserDeleteResponse(messageResourceService.getMessage("user.is.deleted"), userToBeDeleted.getUsername());
    }

    public UserCloseResponse closeUser(Long id) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User closedUser = user.get();
        closedUser.setUserStatus(UserStatus.CLOSED);
        userRepository.save(closedUser);

        return new UserCloseResponse(messageResourceService.getMessage("user.is.closed"), closedUser.getUsername());
    }

    public UserUpdateResponse updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User updatedUser = User.builder()
            .password(userUpdateRequest.getPassword())
            .email(userUpdateRequest.getEmail())
            .name(userUpdateRequest.getName())
            .surname(userUpdateRequest.getSurname()).build();

        userRepository.save(updatedUser);

        return new UserUpdateResponse(messageResourceService.getMessage("user.is.updated"), updatedUser.getUsername());
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}