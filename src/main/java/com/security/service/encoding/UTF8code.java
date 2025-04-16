package com.security.service.encoding;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class UTF8code {

    // 转换为 UTF-8 字节十六进制表示（可视化编码效果）
    public String encode(String input) {
        byte[] utf8Bytes = input.getBytes(StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        for (byte b : utf8Bytes) {
            sb.append(String.format("%02x ", b));
        }
        return sb.toString().trim();
    }

    // 还原 UTF-8 编码字符串（例如："e4 bd a0 e5 a5 bd" → "你好"）
    public String decode(String hexEncoded) {
        String[] hexParts = hexEncoded.split(" ");
        byte[] bytes = new byte[hexParts.length];
        for (int i = 0; i < hexParts.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hexParts[i], 16);
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
