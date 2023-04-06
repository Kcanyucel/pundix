package com.pundix.exception;

public class IllegalArgumentException extends RuntimeException {

    public IllegalArgumentException() {
        super("error.message");
    }
}
