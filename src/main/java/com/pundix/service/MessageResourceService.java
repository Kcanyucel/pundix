package com.pundix.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Locale;

@Service
public class MessageResourceService implements Serializable {

    @Autowired
    private final MessageSource messageSource;

    public MessageResourceService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key) {
        return messageSource.getMessage(key, null, getLocale());
    }

    public String getMessage(String key, Integer... params) {
        return messageSource.getMessage(key, params, getLocale());
    }

    private Locale getLocale() {
        return Locale.getDefault();
    }
}
