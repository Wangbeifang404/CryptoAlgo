package com.security.controller;

import com.security.model.CryptoRequest;
import com.security.model.SignRequest;
import com.security.service.asymmetric.ECC;
import com.security.service.asymmetric.ECDSA;
import com.security.service.asymmetric.RSA;
import com.security.service.asymmetric.RSASHA1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.security.spec.ECPoint;

@RestController
@RequestMapping("/api/asymmetric")
public class AsymmetricController {

    @Autowired
    private RSA rsaService;

    @Autowired
    private RSASHA1 rsaSha1Service;

    @Autowired
    private ECC eccService;

    @Autowired
    private ECDSA ecdsaService;

    // ===== RSA =====
    @PostMapping("/rsa/encrypt")
    public String rsaEncrypt(@RequestBody CryptoRequest request) throws Exception {
        return rsaService.encrypt(request.getInput());
    }

    @PostMapping("/rsa/decrypt")
    public String rsaDecrypt(@RequestBody CryptoRequest request) throws Exception {
        return rsaService.decrypt(request.getInput());
    }

    // ===== RSA-SHA1 =====
    @PostMapping("/rsa-sha1/encrypt")
    public String rsaSha1Encrypt(@RequestBody CryptoRequest request) throws Exception {
        return rsaSha1Service.encrypt(request.getInput());
    }

    @PostMapping("/rsa-sha1/decrypt")
    public String rsaSha1Decrypt(@RequestBody CryptoRequest request) throws Exception {
        return rsaSha1Service.decrypt(request.getInput());
    }

    // ===== ECDSA =====
    @PostMapping("/ecdsa/sign")
    public String ecdsaSign(@RequestBody SignRequest request) throws Exception {
        return ecdsaService.sign(request.getInput());
    }

    @PostMapping("/ecdsa/verify")
    public boolean ecdsaVerify(@RequestBody SignRequest request) throws Exception {
        return ecdsaService.verify(request.getInput(), request.getSignature());
    }

    // ===== ECC 仅用于密钥演示（可扩展为 ECDH）=====
    @GetMapping("/ecc/public-key")
    public String getEccPublicKey() {
        ECPoint ecPoint = (ECPoint) eccService.getPublicKey();  // 获取公钥
        BigInteger x = ecPoint.getAffineX();  // 获取公钥的 X 坐标
        BigInteger y = ecPoint.getAffineY();  // 获取公钥的 Y 坐标
        return "X: " + x.toString(16) + "\nY: " + y.toString(16);  // 返回 X 和 Y 坐标
    }

    @GetMapping("/ecc/private-key")
    public String getEccPrivateKey() {
        BigInteger privateKey = eccService.getPrivateKey();  // 获取私钥
        return privateKey.toString(16);  // 返回私钥的十六进制表示
    }
}