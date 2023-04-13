package com.pundix.entity.builder;

import com.pundix.entity.user.User;
import com.pundix.entity.user.UserRole;
import com.pundix.entity.user.UserStatus;

import java.time.LocalDateTime;

public class UserBuilder {

    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private LocalDateTime createdDate;
    private UserStatus userStatus;
    private UserRole userRole;

    public static UserBuilder userBuilder() {
        return new UserBuilder();
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder createdDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public UserBuilder userStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
        return this;
    }

    public UserBuilder userRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }

    public User build() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setCreatedDate(createdDate);
        user.setUserStatus(userStatus);
        user.setUserRole(userRole);

        return user;
    }
}

