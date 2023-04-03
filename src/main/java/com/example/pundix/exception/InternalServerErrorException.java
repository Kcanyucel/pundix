package com.example.pundix.exception;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String errorMessage) {
        super(errorMessage);
    }
}
