package com.pundix.exception.common;

public class BadRequestExpection extends RuntimeException {

    public BadRequestExpection(String errorMessage) {
        super(errorMessage);
    }
}
