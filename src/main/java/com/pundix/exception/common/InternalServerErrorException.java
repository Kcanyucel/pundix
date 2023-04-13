package com.pundix.exception.common;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String errorMessage) {
        super(errorMessage);
    }
}
