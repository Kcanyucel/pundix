package com.pundix.service;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserSession;
import com.pundix.exception.custom.UserLoginFailedExpection;
import com.pundix.repository.UserRepository;
import com.pundix.request.UserLoginRequest;
import com.pundix.response.UserLoginResponse;
import com.pundix.response.UserLogoutResponse;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService {

    private final UserRepository userRepository;
    private final MessageResourceService messageResourceService;
    private final UserSessionService userSessionService;

    public UserAuthService(UserRepository userRepository, UserSessionService userSessionService, MessageResourceService messageResourceService) {
        this.userRepository = userRepository;
        this.userSessionService = userSessionService;
        this.messageResourceService = messageResourceService;
    }

    public UserLoginResponse login(UserLoginRequest request) {
        User user = User.login(request.getUsername(), request.getPassword());
        boolean usernameOrPasswordExists = userRepository.existsByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (!usernameOrPasswordExists) {
            throw new UserLoginFailedExpection();
        }
        User foundUser = userRepository.findUserByUsername(user.getUsername()).get();
        UserSession sessionInfo = userSessionService.create(foundUser.getId(), foundUser.getUsername());

        return UserLoginResponse.create(messageResourceService.getMessage("user.is.login"), sessionInfo.getLoginDate());
    }

    public UserLogoutResponse logout(String accessToken) {
        UserSession userSessionInfo = userSessionService.logout(accessToken);

        return UserLogoutResponse.create(messageResourceService.getMessage("user.is.logout"), userSessionInfo.getLogoutDate());
    }
}
