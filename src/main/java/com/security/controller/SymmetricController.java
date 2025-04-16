package com.security.controller;

import com.security.service.symmetric.AES;
import com.security.service.symmetric.SM4;
import com.security.service.symmetric.RC6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/symmetric")
public class SymmetricController {

    @Autowired
    private AES aesService;
    @Autowired
    private SM4 sm4Service;
    @Autowired
    private RC6 rc6Service;

    @PostMapping("/aes/encrypt")
    public String encryptAES(@RequestBody Map<String, String> payload) throws Exception {
        return aesService.encrypt(payload.get("input"), payload.get("key"));
    }

    @PostMapping("/aes/decrypt")
    public String decryptAES(@RequestBody Map<String, String> payload) throws Exception {
        return aesService.decrypt(payload.get("input"), payload.get("key"));
    }

    @PostMapping("/sm4/encrypt")
    public String encryptSM4(@RequestBody Map<String, String> payload) throws Exception {
        return sm4Service.encrypt(payload.get("input"), payload.get("key"));
    }

    @PostMapping("/sm4/decrypt")
    public String decryptSM4(@RequestBody Map<String, String> payload) throws Exception {
        return sm4Service.decrypt(payload.get("input"), payload.get("key"));
    }

    @PostMapping("/rc6/encrypt")
    public String encryptRC6(@RequestBody Map<String, String> payload) throws Exception {
        return rc6Service.encrypt(payload.get("input"), payload.get("key"));
    }

    @PostMapping("/rc6/decrypt")
    public String decryptRC6(@RequestBody Map<String, String> payload) throws Exception {
        return rc6Service.decrypt(payload.get("input"), payload.get("key"));
    }
}

