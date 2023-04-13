package com.pundix.validator.user;

import com.pundix.entity.user.UserRole;
import com.pundix.request.UserChangeRoleRequest;
import com.pundix.service.MessageResourceService;
import com.pundix.service.UserService;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserChangeRoleRequestValidator extends BaseValidator {

    private static final int USERNAME_MIN_LENGTH = 5;
    private static final int USERNAME_MAX_LENGTH = 15;

    private final MessageResourceService messageResourceService;
    private final UserService userService;

    public UserChangeRoleRequestValidator(MessageResourceService messageResourceService, UserService userService) {
        this.messageResourceService = messageResourceService;
        this.userService = userService;
    }

    public void validate(UserChangeRoleRequest userChangeRoleRequest) {
        validateUsername(userChangeRoleRequest.getUsername());
    }

    public void validateUsername(String username) {
        validateNotNull(username, messageResourceService.getMessage("error.user.username.null"));
        validateIsBlank(username, messageResourceService.getMessage("error.user.username.empty"));
        validateLengthBetweenInclusive(username, USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH, messageResourceService.getMessage("error.user.username.length", USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH));
        validateNoSpecialCharactersOrTurkishCharacter(username, messageResourceService.getMessage("error.user.username.contains.non.latin.or.special"));

        boolean usernameExist = userService.existsByUsername(username);
        validateFalse(usernameExist, messageResourceService.getMessage("error.user.username.not.exist"));
    }

    public void validateUserRole(UserRole userRole) {
        // Validate !
    }
}
