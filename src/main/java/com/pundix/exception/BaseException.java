package com.pundix.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    private final String messageKey;
    private Object[] params;
    private HttpStatus httpStatus;

    public BaseException(String messageKey, String message, HttpStatus httpStatus) {
        super(message);
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }

    public BaseException(String messageKey, HttpStatus httpStatus, Object... params) {
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
        this.params = params;
    }

    public BaseException(String messageKey) {
        this.messageKey = messageKey;
    }

    public BaseException(String messageKey, Object... params) {
        this.messageKey = messageKey;
        this.params = params;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getParams() {
        return params;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
