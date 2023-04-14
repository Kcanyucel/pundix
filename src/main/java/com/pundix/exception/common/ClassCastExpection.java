package com.pundix.exception.common;

import com.pundix.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

public class ClassCastExpection extends BaseException implements Supplier<ClassCastExpection> {

    public ClassCastExpection() {
        super("error.message", "Class Cast Exception..!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ClassCastExpection get() {
        return null;
    }
}
