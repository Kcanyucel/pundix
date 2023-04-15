package com.pundix.validator.user;

import com.pundix.request.UserLoginRequest;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserLoginRequestValidator extends BaseValidator {

    public void validate(UserLoginRequest request) {
    //    validateNotNull(request.getUsername(), "error.user.username.null");
    //    validateIsBlank(request.getUsername(), "error.user.username.empty");

    //    validateNotNull(request.getPassword(), "error.user.password.null");
   //     validateIsBlank(request.getPassword(), "error.user.password.empty");
    }
}
