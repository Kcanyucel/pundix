package com.pundix.validator.user;

import com.pundix.request.UserUpdateRequest;
import com.pundix.service.ConfigurationService;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateRequestValidator extends BaseValidator {

    ConfigurationService configurationService;

    public UserUpdateRequestValidator(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    public void validate(UserUpdateRequest request) {
        validateNotNull(request.getEmail(), "error.user.email.null");
        validateIsBlank(request.getEmail(), "error.user.email.empty");
        validateRegex(request.getEmail(), "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", "error.user.email.invalid");

        validateNotNull(request.getPassword(), "error.user.password.null");
        validateIsBlank(request.getPassword(), "error.user.password.empty");
        validateNoSpecialCharactersOrTurkishCharacter(request.getPassword(), "error.user.password.contains.non.latin.or.special");
        validateMoreThanOrEquals(request.getPassword(), configurationService.getValue("user.password.min.length"), "error.user.password.min.length");
        validateLessThanOrEquals(request.getPassword(), configurationService.getValue("user.password.max.length"), "error.user.password.max.length");

        if (request.getName() != null) {
            validateLetter(request.getName(), "error.user.name.non.letter");
        }

        if (request.getSurname() != null) {
            validateLetter(request.getName(), "error.user.surname.non.letter");
        }
    }
}

