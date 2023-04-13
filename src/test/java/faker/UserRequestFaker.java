package faker;

import builder.request.UserChangeRoleRequestBuilder;
import builder.request.UserCreateRequestBuilder;
import builder.request.UserLoginRequestBuilder;
import builder.request.UserUpdateRequestBuilder;
import com.pundix.request.UserChangeRoleRequest;
import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;

public class UserRequestFaker extends UserFaker {

    public static UserCreateRequest fromCreate() {
        return new UserCreateRequestBuilder()
            .username(USERNAME)
            .password(PASSWORD)
            .email(EMAIL)
            .name(NAME)
            .surname(SURNAME)
            .build();
    }

    public static UserUpdateRequest fromUpdate() {
        return new UserUpdateRequestBuilder()
            .password(UPDATE_PREFIX + PASSWORD)
            .email(UPDATE_PREFIX + EMAIL)
            .name(UPDATE_PREFIX + NAME)
            .surname(UPDATE_PREFIX + SURNAME)
            .build();
    }

    public static UserLoginRequest fromLogin() {
        return new UserLoginRequestBuilder()
            .username(USERNAME)
            .password(PASSWORD)
            .build();
    }

    public static UserChangeRoleRequest fromChangeRole() {
        return new UserChangeRoleRequestBuilder()
            .username(USERNAME)
            .userRole(USER_ROLE)
            .build();
    }
}
