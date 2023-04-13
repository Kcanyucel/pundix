package com.pundix.exception.common;

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException() {
        super("error.message");
    }
}
