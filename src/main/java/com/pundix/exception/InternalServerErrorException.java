package com.pundix.exception;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String errorMessage) {
        super(errorMessage);
    }
}
