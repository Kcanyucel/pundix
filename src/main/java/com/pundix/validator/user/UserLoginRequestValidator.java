package com.pundix.validator.user;

import com.pundix.request.UserLoginRequest;
import com.pundix.service.MessageResourceService;
import com.pundix.service.PasswordEncoderService;
import com.pundix.service.UserService;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserLoginRequestValidator extends BaseValidator {

    private final MessageResourceService messageResourceService;
    private final UserService userService;
    private final PasswordEncoderService passwordEncoderService;

    public UserLoginRequestValidator(MessageResourceService messageResourceService, UserService userService, PasswordEncoderService passwordEncoderService) {
        this.messageResourceService = messageResourceService;
        this.userService = userService;
        this.passwordEncoderService = passwordEncoderService;
    }

    public void validate(UserLoginRequest userLoginRequest) {
        validateUsername(userLoginRequest.getUsername());
        validatePassword(userLoginRequest.getPassword());
    }

    public void validateUsername(String username) {
        validateNotNull(username, messageResourceService.getMessage("error.user.username.null"));
        validateIsBlank(username, messageResourceService.getMessage("error.user.username.empty"));

        boolean usernameExist = userService.existsByUsername(username);
        validateTrue(usernameExist, messageResourceService.getMessage("error.user.username.not.exist"));
    }

    public void validatePassword(String password) {
        validateNotNull(password, messageResourceService.getMessage("error.user.password.null"));
        validateIsBlank(password, messageResourceService.getMessage("error.user.password.empty"));

        boolean passwordExist = userService.existByPassword(passwordEncoderService.encodePassword(password));
        validateTrue(passwordExist, messageResourceService.getMessage("error.user.password.not.correct"));
    }
}
