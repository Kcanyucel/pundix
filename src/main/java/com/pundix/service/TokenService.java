package com.pundix.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    public String createAccessToken() {
        return generateToken();
    }

    private String generateToken() {
        return UUID.randomUUID().toString() + UUID.randomUUID().toString();
    }
}
