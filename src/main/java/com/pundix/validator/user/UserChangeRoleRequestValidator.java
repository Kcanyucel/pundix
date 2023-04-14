package com.pundix.validator.user;

import com.pundix.request.UserChangeRoleRequest;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserChangeRoleRequestValidator extends BaseValidator {

    public void validate(UserChangeRoleRequest request) {
        validateNotNull(request.getUsername(), "error.user.username.null");
        validateIsBlank(request.getUsername(), "error.user.username.empty");
    }
}
