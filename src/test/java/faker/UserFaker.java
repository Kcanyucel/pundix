package faker;

import com.pundix.entity.User;
import com.pundix.entity.UserStatus;

import java.time.LocalDateTime;

public class UserFaker {

    public static final Long ID = 1L;
    public static final String USERNAME = "jameswilliam";
    public static final String PASSWORD = "james87";
    public static final String EMAIL = "james_william87@outlook.com";
    public static final String NAME = "James";
    public static final String SURNAME = "William";
    public static final String UPDATE_PREFIX = "Updated_";

    public static User generate() {
        return User.builder()
            .id(ID)
            .username(USERNAME)
            .password(PASSWORD)
            .email(EMAIL)
            .name(NAME)
            .surname(SURNAME)
            .userStatus(UserStatus.ACTIVE)
            .createdDate(LocalDateTime.now())
            .build();
    }
}





