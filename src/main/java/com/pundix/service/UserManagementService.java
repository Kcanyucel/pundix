package com.pundix.service;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserSession;
import com.pundix.exception.custom.UserAlreadyExistsException;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.UserCloseResponse;
import com.pundix.response.UserCreateResponse;
import com.pundix.response.UserDeleteResponse;
import com.pundix.response.UserUpdateResponse;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserManagementService {

    private final UserRepository userRepository;
    private final UserSessionService userSessionService;
    private final MessageResourceService messageResourceService;

    public UserManagementService(UserRepository userRepository, UserSessionService userSessionInfoService, MessageResourceService messageResourceService) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionInfoService;
        this.messageResourceService = messageResourceService;
    }

    @Transactional
    public UserCreateResponse create(UserCreateRequest request) {
        User user = User.create(request.getUsername(), request.getPassword(), request.getEmail(), request.getName(), request.getSurname());

        boolean emailOrUsernameExists = userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername());
        if (emailOrUsernameExists) {
            throw new UserAlreadyExistsException();
        }

        User createdUser = userRepository.save(user);
        UserSession userSession = userSessionService.create(createdUser.getId(), createdUser.getUsername());

        return UserCreateResponse.create(createdUser.getId(), createdUser.getUsername(), createdUser.getEmail(), userSession.getAccessToken(), createdUser.getCreatedDate());
    }

    public UserUpdateResponse update(Long id, UserUpdateRequest request) {
        boolean emailExists = userRepository.existsByEmail(request.getEmail().toLowerCase());
        boolean emailExistsToUser = userRepository.existsByIdAndEmail(id, request.getEmail().toLowerCase());

        if (emailExists && !emailExistsToUser) {
            throw new UserAlreadyExistsException();
        }
        User user = userRepository.findUserById(id).get();
        User updatedUser = userRepository.save(User.update(user));

        return UserUpdateResponse.create(messageResourceService.getMessage("user.is.updated"), updatedUser.getUsername(), LocalDateTime.now());
    }

    @Transactional
    public UserDeleteResponse delete(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(user.getId());
        userSessionService.delete(user.getId());

        return UserDeleteResponse.create(messageResourceService.getMessage("user.is.deleted"), LocalDateTime.now());
    }
    @Transactional
    public UserCloseResponse close(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
        User closedUser = User.close(user);

        userRepository.save(closedUser);
        userSessionService.close(closedUser.getId());

        return UserCloseResponse.create(messageResourceService.getMessage("user.is.closed"), LocalDateTime.now());
    }
}
