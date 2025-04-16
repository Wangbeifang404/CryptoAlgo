package com.security.service.hash;

import org.bouncycastle.jcajce.provider.digest.RIPEMD160;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;

@Service
public class SHA {

    public String hashSHA1(String input) throws Exception {
        return hash(input, "SHA-1");
    }

    public String hashSHA256(String input) throws Exception {
        return hash(input, "SHA-256");
    }

    public String hashSHA3(String input) throws Exception {
        return hash(input, "SHA3-256");
    }

    public String hashRIPEMD160(String input) {
        RIPEMD160.Digest digest = new RIPEMD160.Digest();
        byte[] result = digest.digest(input.getBytes());
        return bytesToHex(result);
    }

    private String hash(String input, String algorithm) throws Exception {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] result = digest.digest(input.getBytes());
        return bytesToHex(result);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}