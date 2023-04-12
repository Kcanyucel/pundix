package builder.request;

import com.pundix.request.UserLoginRequest;

public class UserLoginRequestBuilder {

    private String username;
    private String password;

    public static UserLoginRequestBuilder userLoginRequestBuilder() {
        return new UserLoginRequestBuilder();
    }

    public UserLoginRequestBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserLoginRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserLoginRequest build() {
        UserLoginRequest userLoginRequest = new UserLoginRequest();
        userLoginRequest.setUsername(username);
        userLoginRequest.setPassword(password);

        return userLoginRequest;
    }
}
