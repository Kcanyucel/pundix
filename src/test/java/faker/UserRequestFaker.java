package faker;

import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;

public class UserRequestFaker extends UserFaker {

    public static UserCreateRequest fromCreate() {
        return UserCreateRequest.builder()
            .username(USERNAME)
            .password(PASSWORD)
            .email(EMAIL)
            .name(NAME)
            .surname(SURNAME)
            .build();
    }

    public static UserUpdateRequest fromUpdate() {
        return UserUpdateRequest.builder()
            .password(UPDATE_PREFIX + PASSWORD)
            .email(UPDATE_PREFIX + EMAIL)
            .name(UPDATE_PREFIX + NAME)
            .surname(UPDATE_PREFIX + SURNAME)
            .build();
    }
}