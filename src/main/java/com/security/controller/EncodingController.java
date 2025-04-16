package com.security.controller;

import com.security.model.CryptoRequest;
import com.security.service.encoding.Base64code;
import com.security.service.encoding.UTF8code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/encoding")
public class EncodingController {

    @Autowired
    private Base64code base64Service;

    @Autowired
    private UTF8code utf8Service;

    // ===== Base64 编码 =====
    @PostMapping("/base64/encrypt")
    public String base64Encode(@RequestBody CryptoRequest request) {
        return base64Service.encode(request.getInput());
    }

    // ===== Base64 解码 =====
    @PostMapping("/base64/decrypt")
    public String base64Decode(@RequestBody CryptoRequest request) {
        return base64Service.decode(request.getInput());
    }

    // ===== UTF-8 编码（输出16进制）=====
    @PostMapping("/utf8/encrypt")
    public String utf8Encode(@RequestBody CryptoRequest request) {
        return utf8Service.encode(request.getInput());
    }

    // ===== UTF-8 解码（从16进制转回字符串）=====
    @PostMapping("/utf8/decrypt")
    public String utf8Decode(@RequestBody CryptoRequest request) {
        return utf8Service.decode(request.getInput());
    }
}