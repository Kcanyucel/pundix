package com.pundix.service;

import com.pundix.entity.user.UserSession;
import com.pundix.exception.custom.UserSessionInfoNotFoundException;
import com.pundix.repository.UserSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;

    public UserSessionService(UserSessionRepository userSessionInfoRepository) {
        this.userSessionRepository = userSessionInfoRepository;
    }

    public UserSession create(Long id, String username) {
        UserSession userSession = UserSession.create(id, username);

        return userSessionRepository.save(userSession);
    }

    public UserSession logout(String accessToken) {
        boolean accessTokenExists = userSessionRepository.existsUserSessionInfoByAccessToken(accessToken);
        if (!accessTokenExists) {
            throw new UserSessionInfoNotFoundException();
        }
        UserSession userSessionInfo = userSessionRepository.findUserSessionInfoByAccessToken(accessToken).get();
        UserSession.logout(userSessionInfo);

        return userSessionRepository.save(userSessionInfo);
    }

    public void close(Long id) {
        List<UserSession> userSessions = userSessionRepository.findUserSessionsInfoByUserId(id);
        if (userSessions.isEmpty()) {
            throw new UserSessionInfoNotFoundException();
        }
        userSessions.forEach(userSession -> {
            UserSession.logout(userSession);
            userSessionRepository.save(userSession);
        });
    }

    public void delete(Long id) {
        List<UserSession> userSessions = userSessionRepository.findUserSessionsInfoByUserId(id);
        if (userSessions.isEmpty()) {
            throw new UserSessionInfoNotFoundException();
        }
        userSessionRepository.deleteUserSessionsInfoByUserId(id);
    }
}
