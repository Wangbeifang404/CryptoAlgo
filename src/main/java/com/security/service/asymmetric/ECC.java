package com.security.service.asymmetric;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;

@Service
// ECC 服务类
public class ECC {
    private KeyPair keyPair;

    public ECC() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");  // 使用标准命名曲线
        keyGen.initialize(ecSpec);
        this.keyPair = keyGen.generateKeyPair();  // 生成公钥私钥对
    }

    // 获取公钥（返回 X 和 Y 坐标）
    public ECPoint getPublicKey() {
        return ((ECPublicKey) keyPair.getPublic()).getW();  // 返回 ECPoint（包含 X 和 Y 坐标）
    }

    // 获取私钥（返回私钥）
    public BigInteger getPrivateKey() {
        return ((ECPrivateKey) keyPair.getPrivate()).getS();  // 返回私钥（BigInteger）
    }
}
