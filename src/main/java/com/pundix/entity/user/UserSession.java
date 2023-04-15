package com.pundix.entity.user;

import com.pundix.entity.BaseEntity;
import com.pundix.utils.TokenGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_session")
@SequenceGenerator(name = "id_generator", sequenceName = "seq_user_session")
public class UserSession extends BaseEntity {

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;

    @Column(name = "LOGIN_DATE")
    private LocalDateTime loginDate;

    @Column(name = "LOGOUT_DATE")
    private LocalDateTime logoutDate;

    private UserSession() {
    }

    public static UserSession create(Long userId, String username) {
        UserSession userSession = new UserSession();
        userSession.setUserId(userId);
        userSession.setUsername(username);
        userSession.setAccessToken(TokenGenerator.createAccessToken());
        userSession.setLoginDate(LocalDateTime.now());

        return userSession;
    }

    public static UserSession logout(UserSession userSession) {
        userSession.setLogoutDate(LocalDateTime.now());

        return userSession;
    }

    public static UserSession close(UserSession userSession) {
        userSession.setLogoutDate(LocalDateTime.now());

        return userSession;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    private void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    private void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    private void setLogoutDate(LocalDateTime logoutDate) {
        this.logoutDate = logoutDate;
    }
}



