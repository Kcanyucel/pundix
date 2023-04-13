package com.pundix.validator.user;

import com.pundix.request.UserUpdateRequest;
import com.pundix.service.MessageResourceService;
import com.pundix.service.UserService;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateRequestValidator extends BaseValidator {

    private static final int PASSWORD_MIN_LENGTH = 5;
    private static final int PASSWORD_MAX_LENGTH = 20;

    private final MessageResourceService messageResourceService;
    private final UserService userService;

    public UserUpdateRequestValidator(MessageResourceService messageResourceService, UserService userService) {
        this.messageResourceService = messageResourceService;
        this.userService = userService;
    }

    public void validate(UserUpdateRequest userUpdateRequest) {
        validateEmail(userUpdateRequest.getEmail());
        validatePassword(userUpdateRequest.getPassword());
        validateName(userUpdateRequest.getName());
        validateSurname(userUpdateRequest.getSurname());
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
        if (name != null) {
            validateLetter(name, messageResourceService.getMessage("error.user.name.non.letter"));
        }
    }

    public void validateSurname(String surname) {
        if (surname != null) {
            validateLetter(surname, messageResourceService.getMessage("error.user.surname.non.letter"));
        }
    }
}

