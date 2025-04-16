# CryptoAlgoDemo

**CryptoAlgoDemo** 是一个基于 Web 的平台，展示了多种加密算法，用户可以交互式地执行加密、解密、哈希以及编码/解码操作。本项目展示了多种对称和非对称加密算法，包括 AES、RC6、SM4、RSA、ECDSA 等。

## 功能特性

- **对称加密**：AES, RC6, SM4
- **哈希算法**：SHA1, SHA256, SHA3, RIPEMD160, HMAC-SHA1, HMAC-SHA256, PBKDF2
- **编码**：Base64, UTF-8 编码
- **非对称加密**：RSA, RSA-SHA1, ECDSA
- **ECC（椭圆曲线密码学）**：生成并显示公钥和私钥（仅用于演示）

## 技术栈

- **后端**：Spring Boot
- **前端**：HTML, CSS, JavaScript
- **加密库**：BouncyCastle（用于高级加密功能）

## 安装

### 后端

1. 克隆此仓库：
   ```bash
   git clone https://github.com/your-repository-url
   cd your-project-directory
   
2. 构建Spring Boot应用：
   mvn clean package

3. 运行应用
     
