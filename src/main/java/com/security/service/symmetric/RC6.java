package com.security.service.symmetric;

import org.bouncycastle.crypto.engines.RC6Engine;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
public class RC6 {
    public String encrypt(String input, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new RC6Engine());
        cipher.init(true, new KeyParameter(keyBytes));
        byte[] inputBytes = input.getBytes();
        byte[] output = new byte[cipher.getOutputSize(inputBytes.length)];
        int len = cipher.processBytes(inputBytes, 0, inputBytes.length, output, 0);
        len += cipher.doFinal(output, len);
        byte[] encrypted = new byte[len];
        System.arraycopy(output, 0, encrypted, 0, len);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public String decrypt(String encrypted, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(new RC6Engine());
        cipher.init(false, new KeyParameter(keyBytes));
        byte[] encryptedBytes = Base64.getDecoder().decode(encrypted);
        byte[] output = new byte[cipher.getOutputSize(encryptedBytes.length)];
        int len = cipher.processBytes(encryptedBytes, 0, encryptedBytes.length, output, 0);
        len += cipher.doFinal(output, len);
        byte[] decrypted = new byte[len];
        System.arraycopy(output, 0, decrypted, 0, len);
        return new String(decrypted);
    }
}
