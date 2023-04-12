package com.pundix.entity.user;

import com.pundix.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_session_info")
@SequenceGenerator(name = "id_generator", sequenceName = "seq_user_session_info")
public class UserSessionInfo extends BaseEntity {

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    public LocalDateTime getLogoutDate() {
        return logoutDate;
    }

    public void setLogoutDate(LocalDateTime logoutDate) {
        this.logoutDate = logoutDate;
    }
}



