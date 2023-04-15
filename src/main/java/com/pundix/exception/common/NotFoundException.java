package com.pundix.exception.common;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class NotFoundException extends BaseException implements Supplier<NotFoundException> {

    public NotFoundException() {
        super("error.message", "Not Found Exception..!", HttpStatus.NOT_FOUND);
    }

    @Override
    public NotFoundException get() {
        return null;
    }
}
