package com.pundix.validator;

import com.pundix.request.UserRequest;
import com.pundix.service.MessageResourceService;
import com.pundix.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserRequestValidator extends BaseValidator {

    private final MessageResourceService messageResourceService;
    private final UserService userService;

    public UserRequestValidator(MessageResourceService messageResourceService, UserService userService) {
        this.messageResourceService = messageResourceService;
        this.userService = userService;
    }

    public void validateForCreate(UserRequest userRequest) {
        validateUsername(userRequest.username());
        validatePassword(userRequest.password());
        validateEmail(userRequest.email());

        if (!userRequest.name().isBlank() || !userRequest.surname().isBlank()) {
            validateName(userRequest.name());
            validateSurname(userRequest.surname());
        }
    }

    public void validateForUpdate(UserRequest userRequest) {
        boolean emailExists = userService.existsByEmail(userRequest.email());
        validateFalse(emailExists, messageResourceService.getMessage("error.user.email.exist"));
    }

    public void validateUsername(String username) {
        validateNotNull(username, messageResourceService.getMessage("error.user.username.null"));
        validateIsBlank(username, messageResourceService.getMessage("error.user.username.empty"));
        validateLengthBetweenInclusive(username, 5, 20, messageResourceService.getMessage("error.user.username.length", 5, 20));
        validateNoSpecialCharactersOrTurkishCharacter(username, messageResourceService.getMessage("error.user.username.contains.non.latin.or.special"));

        boolean usernameExist = userService.existsByUsername(username);
        validateFalse(usernameExist, messageResourceService.getMessage("error.user.username.exist"));
    }

    public void validatePassword(String password) {
        validateNotNull(password, messageResourceService.getMessage("error.user.password.null"));
        validateIsBlank(password, messageResourceService.getMessage("error.user.password.empty"));
        validateLengthBetweenInclusive(password, 5, 20, messageResourceService.getMessage("error.user.password.length", 5, 20));
        validateNoSpecialCharactersOrTurkishCharacter(password, messageResourceService.getMessage("error.user.password.contains.non.latin.or.special"));
    }

    public void validateEmail(String email) {
        validateNotNull(email, messageResourceService.getMessage("error.user.email.null"));
        validateIsBlank(email, messageResourceService.getMessage("error.user.email.empty"));
        validateEmailCharacters(email, messageResourceService.getMessage("error.user.email.invalid"));

        boolean emailExists = userService.existsByEmail(email);
        validateFalse(emailExists, messageResourceService.getMessage("error.user.email.exist"));
    }

    public void validateName(String name) {
        validateLetter(name, messageResourceService.getMessage("error.user.name.non.letter"));
    }

    public void validateSurname(String surname) {
        validateLetter(surname, messageResourceService.getMessage("error.user.surname.non.letter"));
    }
}



