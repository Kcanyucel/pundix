package com.pundix.exception.common;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class InternalServerErrorException extends BaseException implements Supplier<InternalServerErrorException> {

    public InternalServerErrorException(String errorReason) {
        super("error.message", errorReason, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public InternalServerErrorException get() {
        return null;
    }
}
