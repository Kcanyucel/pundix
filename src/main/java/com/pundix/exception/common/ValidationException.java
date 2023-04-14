package com.pundix.exception.common;

import com.pundix.exception.BaseException;

public class ValidationException extends BaseException {

    public ValidationException(String messageKey) {
        super(messageKey);
    }

    public ValidationException(String messageKey, Object... params) {
        super(messageKey, params);
    }
}
