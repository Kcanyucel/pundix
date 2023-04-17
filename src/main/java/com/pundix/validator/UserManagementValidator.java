package com.pundix.validator;

import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.service.ConfigurationService;
import org.springframework.stereotype.Component;

@Component
public class UserManagementValidator extends BaseValidator {

    ConfigurationService configurationService;

    public UserManagementValidator(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public void validateForCreate(UserCreateRequest request) {
        validateUsername(request.username());
        validateEmail(request.email());
        validatePassword(request.password());
        validateNameAndSurname(request.name(), request.surname());
    }

    public void validateForUpdate(UserUpdateRequest request) {
        validateEmail(request.email());
        validatePassword(request.password());
        validateNameAndSurname(request.name(), request.surname());
    }

    public void validateUsername(String username) {
        validateNoSpecialCharactersOrTurkishCharacter(username, "error.user.username.contains.non.latin.or.special");
        validateMoreThanOrEquals(username, configurationService.getValue("user.username.min.length"), "error.user.username.min.length");
        validateLessThanOrEquals(username, configurationService.getValue("user.username.max.length"), "error.user.username.max.length");
    }

    public void validateNameAndSurname(String name, String surname) {
        if (name != null) {
            validateLetter(name, "error.user.name.non.letter");
        }
        if (surname != null) {
            validateLetter(surname, "error.user.surname.non.letter");
        }
    }

    public void validateEmail(String email) {
        validateRegex(email, "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", "error.user.email.invalid");
    }

    public void validatePassword(String password) {
        validateNoSpecialCharactersOrTurkishCharacter(password, "error.user.password.contains.non.latin.or.special");
        validateMoreThanOrEquals(password, configurationService.getValue("user.password.min.length"), "error.user.password.min.length");
        validateLessThanOrEquals(password, configurationService.getValue("user.password.max.length"), "error.user.password.max.length");
    }
}