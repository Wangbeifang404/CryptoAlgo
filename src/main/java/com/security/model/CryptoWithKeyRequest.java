package com.security.model;

import lombok.Data;

@Data
public class CryptoWithKeyRequest {
    private String input;
    private String key;
}