package com.pundix.exception.common;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class IllegalArgumentException extends BaseException implements Supplier<IllegalArgumentException> {

    public IllegalArgumentException() {
        super("error.message","Illegal Argument Exception..!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public IllegalArgumentException get() {
        return null;
    }
}
