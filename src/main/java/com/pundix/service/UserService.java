package com.pundix.service;

import com.pundix.entity.User;
import com.pundix.entity.UserStatus;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserRequest;
import com.pundix.response.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record UserService(PasswordEncoderService passwordEncoderService, MessageResourceService messageResourceService,
                          UserRepository userRepository) {

    public UserCreateResponse createUser(UserRequest userRequest) {
        String encodingPassword = passwordEncoderService.encodePassword(userRequest.password());
        User user = new User(userRequest.username(), encodingPassword, userRequest.email().toLowerCase(), userRequest.name(), userRequest.surname());
        user.setCreatedDate();
        userRepository.save(user);

        return new UserCreateResponse(user.getId(), user.getUsername(), user.getUsername());
    }

    public UserDetailResponse getUser(Long id) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User foundUser = user.get();
        return new UserDetailResponse(foundUser.getUsername(), foundUser.getEmail(), foundUser.getName(), foundUser.getSurname());
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

    public UserUpdateResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        User userToBeUpdated = user.get();
        User updatedUser = new User(userToBeUpdated.getPassword(), userRequest.email(), userRequest.name(), userRequest.surname());
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