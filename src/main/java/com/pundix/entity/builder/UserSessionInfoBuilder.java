package com.pundix.entity.builder;

import com.pundix.entity.user.UserSessionInfo;

import java.time.LocalDateTime;

public class UserSessionInfoBuilder {

    private Long userId;
    private String username;
    private String accessToken;
    private LocalDateTime loginDate;
    private LocalDateTime logoutDate;

    public static UserSessionInfoBuilder userSessionInfoBuilder() {
        return new UserSessionInfoBuilder();
    }

    public UserSessionInfoBuilder userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserSessionInfoBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserSessionInfoBuilder accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public UserSessionInfoBuilder loginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
        return this;
    }

    public UserSessionInfoBuilder logoutDate(LocalDateTime logoutDate) {
        this.logoutDate = logoutDate;
        return this;
    }

    public UserSessionInfo build() {
        UserSessionInfo userSessionInfo = new UserSessionInfo();
        userSessionInfo.setUserId(userId);
        userSessionInfo.setUsername(username);
        userSessionInfo.setAccessToken(accessToken);
        userSessionInfo.setLoginDate(loginDate);
        userSessionInfo.setLogoutDate(logoutDate);

        return userSessionInfo;
    }
}
