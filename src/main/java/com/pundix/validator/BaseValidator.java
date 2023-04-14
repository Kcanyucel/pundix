package com.pundix.validator;

import com.pundix.exception.common.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BaseValidator {

    public void validateNotNull(Object value, String messageKey) {
        if (Objects.isNull(value)) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateNull(Object value, String messageKey) {
        if (!Objects.isNull(value)) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateIsBlank(String value, String messageKey) {
        if (value.isBlank()) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateBlank(String value, String messageKey) {
        if (!value.isBlank()) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateFalse(boolean condition, String messageKey) {
        if (condition) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateTrue(boolean condition, String messageKey) {
        if (!condition) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateMoreThanOrEquals(String value, String limit, String messageKey) {
        int valueLength = value.length();
        int parseLimit = Integer.parseInt(limit);

        if (valueLength < parseLimit) {
            throw new ValidationException(messageKey, limit);
        }
    }

    public void validateLessThanOrEquals(String value, String limit, String messageKey) {
        int valueLength = value.length();
        int parseLimit = Integer.parseInt(limit);

        if (valueLength > parseLimit) {
            throw new ValidationException(messageKey, limit);
        }
    }

    public void validateRegex(String value, String regexPattern, String messageKey) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateNoSpace(String value, String messageKey) {
        if (value.contains(" ")) {
            throw new ValidationException(messageKey);
        }
    }

    public void validateNoSpecialCharactersOrTurkishCharacter(String value, String messageKey) {
        validateRegex(value, "^[a-zA-Z0-9]*$", messageKey);
    }

    public void validateLetter(String value, String messageKey) {
        validateRegex(value, "^[A-Za-zÇçĞğİıÖöŞşÜü\\s]*$", messageKey);
    }
}

