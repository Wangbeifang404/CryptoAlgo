package com.security.service.asymmetric;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.security.*;
import java.util.Base64;
import java.security.spec.ECGenParameterSpec;

@Service
public class ECDSA {
    private KeyPair keyPair;

    public ECDSA() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC", "BC");
        // ✅ 使用标准命名曲线，不能直接写 keyGen.initialize(160);
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyGen.initialize(ecSpec);
        this.keyPair = keyGen.generateKeyPair();
    }

    // 生成签名
    public String sign(String input) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(keyPair.getPrivate());
        signature.update(input.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }

    // 验证签名
    public boolean verify(String input, String sig) throws Exception {
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initVerify(keyPair.getPublic());
        signature.update(input.getBytes());
        return signature.verify(Base64.getDecoder().decode(sig));
    }
}