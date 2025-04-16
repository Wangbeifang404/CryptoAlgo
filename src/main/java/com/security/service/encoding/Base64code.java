package com.security.service.encoding;

import org.springframework.stereotype.Service;
import java.util.Base64;
import java.nio.charset.StandardCharsets;


@Service
public class Base64code {

    // Base64 编码
    public String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    // Base64 解码
    public String decode(String base64) {
        byte[] decodedBytes = Base64.getDecoder().decode(base64);
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
}