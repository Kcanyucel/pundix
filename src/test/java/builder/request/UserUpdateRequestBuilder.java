package builder.request;

import com.pundix.request.UserUpdateRequest;

public class UserUpdateRequestBuilder {

    private String email;
    private String password;
    private String name;
    private String surname;

    public static UserUpdateRequestBuilder userUpdateRequestBuilder() {
        return new UserUpdateRequestBuilder();
    }

    public UserUpdateRequestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserUpdateRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserUpdateRequestBuilder name(String name) {
        this.name = name;
        return this;
    }

    public UserUpdateRequestBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserUpdateRequest build() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setEmail(email);
        userUpdateRequest.setPassword(password);
        userUpdateRequest.setName(name);
        userUpdateRequest.setSurname(surname);

        return userUpdateRequest;
    }
}
