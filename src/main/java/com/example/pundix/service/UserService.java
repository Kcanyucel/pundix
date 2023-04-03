package com.example.pundix.service;

import com.example.pundix.entity.User;
import com.example.pundix.entity.UserStatus;
import com.example.pundix.exception.NotFoundException;
import com.example.pundix.repository.UserRepository;
import com.example.pundix.request.UserRequest;
import com.example.pundix.response.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public record UserService(PasswordEncoderService passwordEncoderService, MessageResourceService messageResourceService,
                          UserRepository userRepository) {

    public UserCreateResponse createUser(UserRequest userRequest) {
        String encodingPassword = passwordEncoderService.encodePassword(userRequest.password());
        User user = new User(userRequest.username(), encodingPassword, userRequest.email(), userRequest.name(), userRequest.surname());
        userRepository.save(user);

        return new UserCreateResponse(user.id(), user.username(), user.email());
    }

    public UserDetailResponse getUser(Long id) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(messageResourceService.getMessage("error.user.not.exist"));
        }
        User foundUser = user.get();
        return new UserDetailResponse(foundUser.username(), foundUser.email(), foundUser.name(), foundUser.surname());
    }

    public UserDeleteResponse deleteUser(Long id) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(messageResourceService.getMessage("error.user.not.exist"));
        }
        User userToBeDeleted = user.get();
        userRepository.deleteById(id);
        return new UserDeleteResponse(messageResourceService.getMessage("user.is.deleted"), userToBeDeleted.username());
    }

    public UserCloseResponse closeUser(Long id) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(messageResourceService.getMessage("error.user.not.exist"));
        }
        User userToBeClosed = user.get();
        User closedUser = new User(userToBeClosed.username(), userToBeClosed.password(), userToBeClosed.email(), userToBeClosed.name(), userToBeClosed.surname(), UserStatus.CLOSED);
        userRepository.save(closedUser);

        return new UserCloseResponse(messageResourceService.getMessage("user.is.closed"), closedUser.username());
    }

    public UserUpdateResponse updateUser(Long id, UserRequest userRequest) {
        Optional<User> user = getUserById(id);

        if (user.isEmpty()) {
            throw new NotFoundException(messageResourceService.getMessage("error.user.not.exist"));
        }
        User userToBeUpdated = user.get();
        User updatedUser = new User(userToBeUpdated.username(), userToBeUpdated.password(), userRequest.email(), userRequest.name(), userRequest.surname());
        userRepository.save(updatedUser);

        return new UserUpdateResponse(messageResourceService.getMessage("user.is.updated"), updatedUser.username());
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