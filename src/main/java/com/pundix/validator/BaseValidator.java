package com.pundix.validator;

import com.pundix.entity.User;
import com.pundix.exception.BadRequestExpection;
import com.pundix.exception.custom.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BaseValidator {

    public void validateNotNull(Object value, String errorMessage) {
        if (Objects.isNull(value)) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateNull(Object value, String errorMessage) {
        if (!Objects.isNull(value)) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateIsBlank(String value, String errorMessage) {
        if (value.isBlank()) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateFalse(boolean condition, String errorMessage) {
        if (condition) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateTrue(boolean condition, String errorMessage) {
        if (!condition) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateMoreThanOrEquals(Comparable value, Comparable limit, String errorMessage) {
        if (limit.compareTo(value) > 0) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateLessThanOrEquals(Comparable value, Comparable limit, String errorMessage) {
        if (value.compareTo(limit) > 0) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateLengthBetweenInclusive(String value, int lowerLimit, int upperLimit, String errorMessage) {
        int length = value.length();
        validateMoreThanOrEquals(length, lowerLimit, errorMessage);
        validateLessThanOrEquals(length, upperLimit, errorMessage);
    }

    public void validateRegex(String value, String regexPattern, String errorMessage) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateNoSpace(String value, String errorMessage) {
        if (value.contains(" ")) {
            throw new BadRequestExpection(errorMessage);
        }
    }

    public void validateNoSpecialCharactersOrTurkishCharacter(String value, String errorMessage) {
        validateRegex(value, "^[a-zA-Z0-9]*$", errorMessage);
    }

    public void validateLetter(String value, String errorMessage) {
        validateRegex(value, "^[a-zA-Z]+$", errorMessage);
    }

    public void validateUserEmpty(Optional<User> user){
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
    }
}

