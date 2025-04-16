package com.security.controller;

import com.security.model.CryptoRequest;
import com.security.model.CryptoWithKeyRequest;
import com.security.service.hash.HMAC;
import com.security.service.hash.PBKDF2;
import com.security.service.hash.SHA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hash")
public class HashController {

    @Autowired
    private SHA shaService;

    @Autowired
    private HMAC hmacService;

    @Autowired
    private PBKDF2 pbkdf2Service;

    // ===== SHA 系列 =====
    @PostMapping("/sha1")
    public String sha1(@RequestBody CryptoRequest request) throws Exception {
        return shaService.hashSHA1(request.getInput());
    }

    @PostMapping("/sha256")
    public String sha256(@RequestBody CryptoRequest request) throws Exception {
        return shaService.hashSHA256(request.getInput());
    }

    @PostMapping("/sha3")
    public String sha3(@RequestBody CryptoRequest request) throws Exception {
        return shaService.hashSHA3(request.getInput());
    }

    @PostMapping("/ripemd160")
    public String ripemd160(@RequestBody CryptoRequest request) {
        return shaService.hashRIPEMD160(request.getInput());
    }

    // ===== HMAC 系列 =====
    @PostMapping("/hmac-sha1")
    public String hmacSha1(@RequestBody CryptoWithKeyRequest request) throws Exception {
        return hmacService.hmacSHA1(request.getInput(), request.getKey());
    }

    @PostMapping("/hmac-sha256")
    public String hmacSha256(@RequestBody CryptoWithKeyRequest request) throws Exception {
        return hmacService.hmacSHA256(request.getInput(), request.getKey());
    }

    // ===== PBKDF2 =====
    @PostMapping("/pbkdf2")
    public String pbkdf2(@RequestBody CryptoWithKeyRequest request) throws Exception {
        return pbkdf2Service.pbkdf2(request.getInput(), request.getKey()); // key 作为 salt
    }
}