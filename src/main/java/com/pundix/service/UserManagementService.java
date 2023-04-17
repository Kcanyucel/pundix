package com.pundix.service;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserSession;
import com.pundix.exception.custom.UserAlreadyExistsException;
import com.pundix.exception.custom.UserEmailAlreadyExistsException;
import com.pundix.exception.custom.UserNotFoundException;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.response.UserCloseResponse;
import com.pundix.response.UserCreateResponse;
import com.pundix.response.UserDeleteResponse;
import com.pundix.response.UserUpdateResponse;
import com.pundix.validator.UserManagementValidator;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserManagementService {

    private final UserRepository userRepository;
    private final UserSessionService userSessionService;
    private final UserManagementValidator userManagementValidator;
    private final MessageResourceService messageResourceService;

    public UserManagementService(UserRepository userRepository,
                                 UserSessionService userSessionInfoService,
                                 UserManagementValidator userManagementValidator,
                                 MessageResourceService messageResourceService) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionInfoService;
        this.userManagementValidator = userManagementValidator;
        this.messageResourceService = messageResourceService;
    }

    @Transactional
    public UserCreateResponse create(UserCreateRequest request) {
        userManagementValidator.validateForCreate(request);

        boolean emailOrUsernameExists = userRepository.existsByEmailOrUsername(request.email().toLowerCase(), request.username().toLowerCase());
        if (emailOrUsernameExists) {
            throw new UserAlreadyExistsException();
        }
        User user = User.create(request.username(), request.password(), request.email(), request.name(), request.surname());
        User createdUser = userRepository.save(user);
        UserSession userSession = userSessionService.create(createdUser.getId(), createdUser.getUsername());

        return new UserCreateResponse(createdUser.getId(), createdUser.getUsername(), createdUser.getEmail(), userSession.getAccessToken(), createdUser.getCreatedDate());
    }

    public UserUpdateResponse update(Long id, UserUpdateRequest request) {
        userManagementValidator.validateForUpdate(request);
        boolean emailExists = userRepository.existsByEmail(request.email().toLowerCase());
        boolean emailExistsToUser = userRepository.existsByIdAndEmail(id, request.email().toLowerCase());

        if (emailExists && !emailExistsToUser) {
            throw new UserEmailAlreadyExistsException();
        }
        User user = userRepository.findUserById(id).orElseThrow(new UserNotFoundException());
        User updatedUser = userRepository.save(User.update(user));

        return new UserUpdateResponse(messageResourceService.getMessage("user.is.updated"), updatedUser.getUsername(), LocalDateTime.now());
    }

    @Transactional
    public UserDeleteResponse delete(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(user.getId());
        userSessionService.delete(user.getId());

        return new UserDeleteResponse(messageResourceService.getMessage("user.is.deleted"), LocalDateTime.now());
    }

    @Transactional
    public UserCloseResponse close(Long id) {
        User user = userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
        User closedUser = User.close(user);

        userRepository.save(closedUser);
        userSessionService.close(closedUser.getId());

        return new UserCloseResponse(messageResourceService.getMessage("user.is.closed"), LocalDateTime.now());
    }
}
