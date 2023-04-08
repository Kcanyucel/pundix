package com.pundix.validator.user;

import com.pundix.request.UserCreateRequest;
import com.pundix.request.UserLoginRequest;
import com.pundix.request.UserUpdateRequest;
import com.pundix.service.MessageResourceService;
import com.pundix.service.PasswordEncoderService;
import com.pundix.service.UserService;
import com.pundix.validator.BaseValidator;
import org.springframework.stereotype.Component;

@Component
public class UserValidator extends BaseValidator {

    private final MessageResourceService messageResourceService;
    private final UserService userService;
    private final PasswordEncoderService passwordEncoderService;

    public UserValidator(MessageResourceService messageResourceService, UserService userService, PasswordEncoderService passwordEncoderService) {
        this.messageResourceService = messageResourceService;
        this.userService = userService;
        this.passwordEncoderService = passwordEncoderService;
    }

    public void validateForCreate(UserCreateRequest userCreateRequest) {
        validateEmailNotNull(userCreateRequest.getEmail());
        validateEmailIsBlank(userCreateRequest.getEmail());
        validateEmailExist(userCreateRequest.getEmail());
        validateEmailCharacters(userCreateRequest.getEmail());

        validateUsernameNotNull(userCreateRequest.getUsername());
        validateUsernameIsBlank(userCreateRequest.getUsername());
        validateUsernameNoSpecialCharactersOrTurkishCharacter(userCreateRequest.getUsername());
        validateUsernameLengthBetweenInclusive(userCreateRequest.getUsername());
        validateUsernameExist(userCreateRequest.getUsername());

        validatePasswordNotNull(userCreateRequest.getPassword());
        validatePasswordIsBlank(userCreateRequest.getPassword());
        validatePasswordLengthBetweenInclusive(userCreateRequest.getPassword());
        validatePasswordNoSpecialCharactersOrTurkishCharacter(userCreateRequest.getPassword());

        if (!userCreateRequest.getName().isBlank() || !userCreateRequest.getSurname().isBlank()) {
            validateNameLetter(userCreateRequest.getName());
            validateSurnameLetter(userCreateRequest.getSurname());
        }
    }

    public void validateForLogin(UserLoginRequest userLoginRequest) {
        validateUsernameNotNull(userLoginRequest.getUsername());
        validateUsernameIsBlank(userLoginRequest.getUsername());
        validateUsernameNotExist(userLoginRequest.getUsername());

        validatePasswordNotNull(userLoginRequest.getPassword());
        validatePasswordIsBlank(userLoginRequest.getPassword());
        validatePasswordNotExist(userLoginRequest.getPassword());
    }

    public void validateForUpdate(UserUpdateRequest userUpdateRequest) {
        validateEmailNotNull(userUpdateRequest.getEmail());
        validateEmailIsBlank(userUpdateRequest.getEmail());
        validateEmailExist(userUpdateRequest.getEmail());
        validateEmailCharacters(userUpdateRequest.getEmail());

        validatePasswordNotNull(userUpdateRequest.getPassword());
        validatePasswordIsBlank(userUpdateRequest.getPassword());
        validatePasswordLengthBetweenInclusive(userUpdateRequest.getPassword());
        validatePasswordNoSpecialCharactersOrTurkishCharacter(userUpdateRequest.getPassword());

        if (!userUpdateRequest.getName().isBlank() || !userUpdateRequest.getSurname().isBlank()) {
            validateNameLetter(userUpdateRequest.getName());
            validateSurnameLetter(userUpdateRequest.getSurname());
        }
    }

    public void validateUsernameNotNull(String username) {
        validateNotNull(username, messageResourceService.getMessage("error.user.username.null"));
    }

    public void validateUsernameIsBlank(String username) {
        validateIsBlank(username, messageResourceService.getMessage("error.user.username.empty"));
    }

    public void validateUsernameLengthBetweenInclusive(String username) {
        validateLengthBetweenInclusive(username, 5, 20, messageResourceService.getMessage("error.user.username.length", 5, 20));
    }

    public void validateUsernameNoSpecialCharactersOrTurkishCharacter(String username) {
        validateNoSpecialCharactersOrTurkishCharacter(username, messageResourceService.getMessage("error.user.username.contains.non.latin.or.special"));
    }

    public void validateEmailNotNull(String email) {
        validateNotNull(email, messageResourceService.getMessage("error.user.email.null"));
    }

    public void validateEmailIsBlank(String email) {
        validateIsBlank(email, messageResourceService.getMessage("error.user.email.empty"));
    }

    public void validateEmailCharacters(String email) {
        validateRegex(email, "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", messageResourceService.getMessage("error.user.email.invalid"));
    }

    public void validatePasswordNotNull(String password) {
        validateNotNull(password, messageResourceService.getMessage("error.user.password.null"));
    }

    public void validatePasswordIsBlank(String password) {
        validateIsBlank(password, messageResourceService.getMessage("error.user.password.empty"));
    }

    public void validatePasswordLengthBetweenInclusive(String password) {
        validateLengthBetweenInclusive(password, 5, 20, messageResourceService.getMessage("error.user.password.length", 5, 20));
    }

    public void validatePasswordNoSpecialCharactersOrTurkishCharacter(String password) {
        validateNoSpecialCharactersOrTurkishCharacter(password, messageResourceService.getMessage("error.user.password.contains.non.latin.or.special"));
    }

    public void validateNameLetter(String name) {
        validateLetter(name, messageResourceService.getMessage("error.user.name.non.letter"));
    }

    public void validateSurnameLetter(String surname) {
        validateLetter(surname, messageResourceService.getMessage("error.user.surname.non.letter"));
    }

    public void validateUsernameExist(String username) {
        boolean usernameExist = userService.existsByUsername(username);
        validateFalse(usernameExist, messageResourceService.getMessage("error.user.username.exist"));
    }

    public void validateUsernameNotExist(String username) {
        boolean usernameExist = userService.existsByUsername(username);
        validateTrue(usernameExist, messageResourceService.getMessage("error.user.username.not.exist"));
    }

    public void validateEmailExist(String email) {
        boolean emailExists = userService.existsByEmail(email);
        validateFalse(emailExists, messageResourceService.getMessage("error.user.email.exist"));
    }

    private void validatePasswordNotExist(String password) {
        String encodedpassword = passwordEncoderService.encodePassword(password);

        boolean passwordExist = userService.existByPassword(encodedpassword);
        validateTrue(passwordExist, messageResourceService.getMessage("error.user.password.not.correct"));
    }
}
