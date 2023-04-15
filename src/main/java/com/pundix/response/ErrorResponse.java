package com.pundix.response;

public record ErrorResponse(String message, int statusCode) {

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

