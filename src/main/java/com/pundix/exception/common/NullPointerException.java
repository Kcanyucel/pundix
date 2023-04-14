package com.pundix.exception.common;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class NullPointerException extends BaseException implements Supplier<NotFoundException> {

    public NullPointerException() {
        super("error.message", "Null Pointer Exception..!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public NotFoundException get() {
        return null;
    }
}
