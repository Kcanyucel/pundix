package com.pundix.validator;

import com.pundix.request.UserRequest;
import com.pundix.service.MessageResourceService;
import com.pundix.service.UserService;
import org.springframework.stereotype.Component;

@Component
public record UserRequestValidator(MessageResourceService messageResourceService, UserService userService,
                                   BaseValidator baseValidator) {

    public void validateForCreate(UserRequest userRequest) {
        validateUsername(userRequest.username());
        validatePassword(userRequest.password());
        validateEmail(userRequest.email());

        if (userRequest.name().isBlank() || userRequest.surname().isBlank()) {
            validateName(userRequest.name());
            validateSurname(userRequest.surname());
        }
    }

    public void validateUsername(String username) {
        baseValidator.validateNoSpecialCharactersOrTurkishCharacter(username, messageResourceService.getMessage("error.user.username.contains.non.latin.or.special"));

        boolean usernameExist = userService.existsByUsername(username);
        baseValidator.validateFalse(usernameExist, messageResourceService.getMessage("error.user.username.exist"));
    }

    public void validatePassword(String password) {
        baseValidator.validateNoSpecialCharactersOrTurkishCharacter(password, messageResourceService.getMessage("error.user.password.contains.non.latin.or.special"));
    }

    public void validateEmail(String email) {
        baseValidator.validateEmailCharacters(email, messageResourceService.getMessage("error.user.email.invalid"));

        boolean emailExists = userService.existsByEmail(email);
        baseValidator.validateFalse(emailExists, messageResourceService.getMessage("error.user.email.exist"));
    }

    public void validateName(String name) {
        baseValidator.validateLetter(name, messageResourceService.getMessage("error.user.name.non.letter"));
    }

    public void validateSurname(String surname) {
        baseValidator.validateLetter(surname, messageResourceService.getMessage("error.user.surname.non.letter"));
    }
}



