package com.pundix.exception;

public class BadRequestExpection extends RuntimeException {

    public BadRequestExpection(String errorMessage) {
        super(errorMessage);
    }
}
