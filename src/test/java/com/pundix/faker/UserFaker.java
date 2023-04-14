package com.pundix.faker;

import com.pundix.entity.user.UserRole;
import com.pundix.entity.user.UserStatus;

import java.time.LocalDateTime;

public class UserFaker {

    public static final String USERNAME = "Jameswilliam";
    public static final String PASSWORD = "james87";
    public static final String EMAIL = "james_william87@outlook.com";
    public static final String NAME = "James";
    public static final String SURNAME = "William";
    public static final UserStatus USER_STATUS = UserStatus.ACTIVE;
    public static final LocalDateTime CREATED_DATE = LocalDateTime.now();
    public static final UserRole USER_ROLE = UserRole.COSTUMER;
    public static final String UPDATE_PREFIX = "Updated_";

    public UserFaker create() {
        return this;

    }
}




