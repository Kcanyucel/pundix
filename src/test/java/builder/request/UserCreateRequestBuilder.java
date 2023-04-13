package builder.request;

import com.pundix.request.UserCreateRequest;

public class UserCreateRequestBuilder {

    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;

    public static UserCreateRequestBuilder userCreateRequestBuilder() {
        return new UserCreateRequestBuilder();
    }

    public UserCreateRequestBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserCreateRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserCreateRequestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserCreateRequestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserCreateRequestBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserCreateRequest build() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername(username);
        userCreateRequest.setPassword(password);
        userCreateRequest.setEmail(email);
        userCreateRequest.setName(name);
        userCreateRequest.setSurname(surname);

        return userCreateRequest;
    }
}
