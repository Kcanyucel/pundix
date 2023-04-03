package com.example.pundix.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public record MessageResourceService(MessageSource messageSource) {

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, getLocale());
    }

    public String getMessage(String key, String... params) {
        return messageSource.getMessage(key, params, getLocale());
    }

    public String getMessage(String key, Integer... params) {
        return messageSource.getMessage(key, params, getLocale());
    }

    private Locale getLocale() {
        return Locale.getDefault();
    }
}
