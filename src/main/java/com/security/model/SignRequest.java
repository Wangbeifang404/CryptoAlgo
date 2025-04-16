package com.security.model;

import lombok.Data;

@Data
public class SignRequest {
    private String input;     // 原文
    private String signature; // 签名（用于验证）
}