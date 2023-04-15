package com.pundix.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class PasswordEncoder {

    public static String encodePassword(String password) {
        return Base64.encodeBase64String(DigestUtils.getSha512Digest().digest(password.getBytes()));
    }
}
