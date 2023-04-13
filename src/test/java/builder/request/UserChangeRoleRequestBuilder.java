package builder.request;

import com.pundix.entity.user.UserRole;
import com.pundix.request.UserChangeRoleRequest;

public class UserChangeRoleRequestBuilder {

    private String username;
    private UserRole userRole;

    public static UserChangeRoleRequestBuilder userChangeRoleRequestBuilder() {
        return new UserChangeRoleRequestBuilder();
    }

    public UserChangeRoleRequestBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserChangeRoleRequestBuilder userRole(UserRole userRole) {
        this.userRole = userRole;
        return this;
    }
    public UserChangeRoleRequest build() {
        UserChangeRoleRequest userChangeRoleRequest = new UserChangeRoleRequest();
        userChangeRoleRequest.setUsername(username);
        userChangeRoleRequest.setUserRole(userRole);

        return userChangeRoleRequest;
    }
}
