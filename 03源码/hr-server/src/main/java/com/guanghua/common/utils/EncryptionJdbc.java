package com.guanghua.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;

import javax.annotation.Resource;

@Slf4j
public class EncryptionJdbc {
    @Resource
    private StringEncryptor stringEncryptor;

    public void encryptionJdbc() {
        String username = stringEncryptor.encrypt("root");
        log.info("username:" + username);        //加密123
        String password = stringEncryptor.encrypt("admin");
        log.info("password:" + password);
        String url = stringEncryptor.encrypt("jdbc:mysql://localhost:3306/hr");
        log.info("url:" + url);
    }
}
