package com.pundix.service.user;

import com.pundix.entity.user.User;
import com.pundix.exception.custom.UserAlreadyExistsException;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.UserUpdateResponse;
import com.pundix.service.MessageResourceService;
import com.pundix.service.PasswordEncoderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserUpdateService {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final MessageResourceService messageResourceService;

    public UserUpdateService(UserRepository userRepository, PasswordEncoderService passwordEncoderService, MessageResourceService messageResourceService) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.messageResourceService = messageResourceService;
    }

    public UserUpdateResponse update(Long id, UserUpdateRequest request) {
        boolean emailExists = userRepository.existsByEmail(request.getEmail().toLowerCase());
        boolean emailExistsToUser = userRepository.existsByIdAndEmail(id, request.getEmail().toLowerCase());

        if (emailExists && !emailExistsToUser) {
            throw new UserAlreadyExistsException();
        }
        User user = userRepository.findUserById(id).get();
        User updatedUser = userRepository.save(build(user, request));

        return UserUpdateResponse.create(messageResourceService.getMessage("user.is.updated"), updatedUser.getUsername(), LocalDateTime.now());
    }

    private User build(User user, UserUpdateRequest request) {
        user.setPassword(passwordEncoderService.encodePassword(request.getPassword()));
        user.setEmail(request.getEmail().toLowerCase());
        user.setName(request.getName());
        user.setSurname(request.getSurname());

        return user;
    }
}

