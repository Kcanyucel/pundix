package com.pundix.service;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoderService {

    public String encodePassword(String password) {
        return Base64.encodeBase64String(DigestUtils.getSha512Digest().digest(password.getBytes()));
    }
}
