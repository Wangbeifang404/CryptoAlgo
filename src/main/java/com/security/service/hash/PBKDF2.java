package com.security.service.hash;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;

@Service
public class PBKDF2 {

    public String pbkdf2(String input, String salt) throws Exception {
        int iterations = 10000;
        int keyLength = 256;

        PBEKeySpec spec = new PBEKeySpec(
                input.toCharArray(),
                salt.getBytes(),
                iterations,
                keyLength
        );
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return Base64.getEncoder().encodeToString(hash);
    }
}