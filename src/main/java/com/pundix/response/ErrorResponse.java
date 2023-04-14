package com.pundix.response;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

    private final String message;
    private final HttpStatus errorCode;

    public ErrorResponse(String message, HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return errorCode;
    }
}

