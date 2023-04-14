package com.pundix.exception.common;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class IllegalStateException extends BaseException implements Supplier<IllegalStateException> {

    public IllegalStateException() {
        super("error.message", "Illegal State Exception..!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public IllegalStateException get() {
        return this;
    }
}
