package com.pundix.utils;

import java.util.UUID;

public class TokenGenerator {

    public static String createAccessToken() {
        return generateToken();
    }

    private static String generateToken() {
        return UUID.randomUUID() + UUID.randomUUID().toString();
    }
}
