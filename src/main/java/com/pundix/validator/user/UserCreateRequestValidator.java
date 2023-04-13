package com.pundix.validator.user;

import com.pundix.request.UserCreateRequest;
import com.pundix.service.MessageResourceService;
import com.pundix.service.UserService;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserCreateRequestValidator extends BaseValidator {

    private static final int USERNAME_MIN_LENGTH = 5;
    private static final int USERNAME_MAX_LENGTH = 15;
    private static final int PASSWORD_MIN_LENGTH = 5;
    private static final int PASSWORD_MAX_LENGTH = 20;

    private final MessageResourceService messageResourceService;
    private final UserService userService;

    public UserCreateRequestValidator(MessageResourceService messageResourceService, UserService userService) {
        this.messageResourceService = messageResourceService;
        this.userService = userService;
    }

    public void validate(UserCreateRequest userCreateRequest) {
        validateUsername(userCreateRequest.getUsername());
        validateEmail(userCreateRequest.getEmail());
        validatePassword(userCreateRequest.getPassword());
        //    validateName(userCreateRequest.getName());
        //    validateSurname(userCreateRequest.getSurname());
    }

    public void validateUsername(String username) {
        validateNotNull(username, messageResourceService.getMessage("error.user.username.null"));
        validateIsBlank(username, messageResourceService.getMessage("error.user.username.empty"));
        validateLengthBetweenInclusive(username, USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH, messageResourceService.getMessage("error.user.username.length", USERNAME_MIN_LENGTH, USERNAME_MAX_LENGTH));
        validateNoSpecialCharactersOrTurkishCharacter(username, messageResourceService.getMessage("error.user.username.contains.non.latin.or.special"));

        boolean usernameExist = userService.existsByUsername(username);
        validateFalse(usernameExist, messageResourceService.getMessage("error.user.username.exist"));
    }

    public void validateEmail(String email) {
        validateNotNull(email, messageResourceService.getMessage("error.user.email.null"));
        validateIsBlank(email, messageResourceService.getMessage("error.user.email.empty"));
        validateRegex(email, "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", messageResourceService.getMessage("error.user.email.invalid"));

        boolean emailExists = userService.existsByEmail(email);
        validateFalse(emailExists, messageResourceService.getMessage("error.user.email.exist"));
    }

    public void validatePassword(String password) {
        validateNotNull(password, messageResourceService.getMessage("error.user.password.null"));
        validateIsBlank(password, messageResourceService.getMessage("error.user.password.empty"));
        validateLengthBetweenInclusive(password, PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH, messageResourceService.getMessage("error.user.password.length", PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH));
        validateNoSpecialCharactersOrTurkishCharacter(password, messageResourceService.getMessage("error.user.password.contains.non.latin.or.special"));
    }

    public void validateName(String name) {
        if (!name.isBlank()) {
            validateLetter(name, messageResourceService.getMessage("error.user.name.non.letter"));
        }
    }

    public void validateSurname(String surname) {
        if (!surname.isBlank()) {
            validateLetter(surname, messageResourceService.getMessage("error.user.surname.non.letter"));
        }
    }
}
