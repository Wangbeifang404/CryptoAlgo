package com.security.service.hash;

import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class HMAC {

    public String hmacSHA1(String input, String key) throws Exception {
        return hmac(input, key, "HmacSHA1");
    }

    public String hmacSHA256(String input, String key) throws Exception {
        return hmac(input, key, "HmacSHA256");
    }

    private String hmac(String input, String key, String algorithm) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(secretKey);
        byte[] result = mac.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(result);
    }
}