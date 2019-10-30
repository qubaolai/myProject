package com.qubaolai.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

import javax.annotation.Resource;

@Slf4j
public class EncryptionJdbc {
    @Resource
    private StringEncryptor stringEncryptor;

    public void encryptionJdbc() {
        String username = stringEncryptor.encrypt("root");
        String password = stringEncryptor.encrypt("admin");
        String url = stringEncryptor.encrypt("jdbc:mysql://localhost:3306/hr");
    }
}
