package com.pundix.service.user;

import com.pundix.entity.user.User;
import com.pundix.entity.user.session.UserSession;
import com.pundix.exception.custom.UserLoginFailedExpection;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserLoginRequest;
import com.pundix.response.UserLoginResponse;
import com.pundix.service.MessageResourceService;
import com.pundix.service.PasswordEncoderService;
import com.pundix.service.user.session.UserSessionService;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final UserSessionService userSessionService;
    private final MessageResourceService messageResourceService;

    public UserLoginService(UserRepository userRepository, PasswordEncoderService passwordEncoderService, UserSessionService userSessionService, MessageResourceService messageResourceService) {
        this.userRepository = userRepository;
        this.passwordEncoderService = passwordEncoderService;
        this.userSessionService = userSessionService;
        this.messageResourceService = messageResourceService;
    }

    public UserLoginResponse login(UserLoginRequest request) {
        User loginUser = build(request);
        boolean usernameOrPasswordExists = userRepository.existsByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());

        if (!usernameOrPasswordExists) {
            throw new UserLoginFailedExpection();
        }
        User user = userRepository.findUserByUsername(loginUser.getUsername()).get();
        UserSession sessionInfo = userSessionService.create(user.getId(), user.getUsername());

        return UserLoginResponse.create(messageResourceService.getMessage("user.is.login"), sessionInfo.getLoginDate());
    }

    private User build(UserLoginRequest request) {
        User user = new User();
        user.setUsername(request.getUsername().toLowerCase());
        user.setPassword(passwordEncoderService.encodePassword(request.getPassword()));

        return user;
    }
}
