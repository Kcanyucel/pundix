package faker;

import com.pundix.entity.builder.UserSessionInfoBuilder;
import com.pundix.entity.user.UserSessionInfo;

public class UserSessionInfoFaker {

    public static final Long USER_ID = 1L;
    public static final String USERNAME = "jameswilliam";
    public static final String ACCESS_TOKEN = "2a74b4cb-08c1-4183-9de3-9a519d5cfk6a00525f74-cd43-4d98-b37a-f89925e708e9";

    public static UserSessionInfo create() {
        return new UserSessionInfoBuilder()
            .userId(USER_ID)
            .username(USERNAME)
            .accessToken(ACCESS_TOKEN)
            .build();
    }
}
