package com.pundix.service.user;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserRole;
import com.pundix.entity.user.UserStatus;
import com.pundix.entity.user.session.UserSession;
import com.pundix.exception.custom.UserAlreadyExistsException;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserCreateRequest;
import com.pundix.response.UserCreateResponse;
import com.pundix.service.PasswordEncoderService;
import com.pundix.service.user.session.UserSessionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserCreateService {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final UserSessionService userSessionService;

    public UserCreateService(UserRepository userRepository, PasswordEncoderService passwordEncoderService, UserSessionService userSessionInfoService) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.userSessionService = userSessionInfoService;
    }

    @Transactional
    public UserCreateResponse create(UserCreateRequest request) {
        User user = build(request);

        boolean emailOrUsernameExists = userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername());
        if (emailOrUsernameExists) {
            throw new UserAlreadyExistsException();
        }

        User createdUser = userRepository.save(user);
        UserSession userSession = userSessionService.create(createdUser.getId(), createdUser.getUsername());

        return UserCreateResponse.create(createdUser.getId(), createdUser.getUsername(), createdUser.getEmail(), userSession.getAccessToken(), createdUser.getCreatedDate());
    }

    private User build(UserCreateRequest request) {
        User user = new User();
        user.setUsername(request.getUsername().toLowerCase());
        user.setPassword(passwordEncoderService.encodePassword(request.getPassword()));
        user.setEmail(request.getEmail().toLowerCase());
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setUserRole(UserRole.COSTUMER);
        user.setCreatedDate(LocalDateTime.now());
        user.setUserStatus(UserStatus.ACTIVE);

        return user;
    }
}
