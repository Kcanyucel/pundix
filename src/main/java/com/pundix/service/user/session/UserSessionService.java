package com.pundix.service.user.session;

import com.pundix.entity.user.session.UserSession;
import com.pundix.exception.custom.UserSessionInfoNotFoundException;
import com.pundix.repository.UserSessionRepository;
import com.pundix.service.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserSessionService {

    private final UserSessionRepository userSessionRepository;
    private final TokenService tokenService;

    public UserSessionService(UserSessionRepository userSessionInfoRepository, TokenService tokenService) {
        this.userSessionRepository = userSessionInfoRepository;
        this.tokenService = tokenService;
    }

    public UserSession create(Long id, String username) {
        final UserSession userSessionInfo = build(id, username);
        return userSessionRepository.save(userSessionInfo);
    }

    public UserSession logout(String accessToken) {
        boolean accessTokenExists = userSessionRepository.existsUserSessionInfoByAccessToken(accessToken);
        if (!accessTokenExists) {
            throw new UserSessionInfoNotFoundException();
        }
        UserSession userSessionInfo = userSessionRepository.findUserSessionInfoByAccessToken(accessToken).get();
        userSessionInfo.setLogoutDate(LocalDateTime.now());

        return userSessionRepository.save(userSessionInfo);
    }

    public void close(Long id) {
        List<UserSession> userSessions = userSessionRepository.findUserSessionsInfoByUserId(id);
        if (userSessions.isEmpty()) {
            throw new UserSessionInfoNotFoundException();
        }
        userSessions.forEach(userSession -> {
            userSession.setLogoutDate(LocalDateTime.now());
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

    private UserSession build(Long id, String username) {
        UserSession userSessionInfo = new UserSession();
        userSessionInfo.setUserId(id);
        userSessionInfo.setUsername(username);
        userSessionInfo.setAccessToken(tokenService.createAccessToken());
        userSessionInfo.setLoginDate(LocalDateTime.now());

        return userSessionInfo;
    }
}
