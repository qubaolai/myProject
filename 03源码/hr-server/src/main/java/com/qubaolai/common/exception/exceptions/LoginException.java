package com.qubaolai.common.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qubaolai
 * @date 2019/10/30 19:17
 */
@Slf4j
public class LoginException extends RuntimeException{
    private Integer code;
    private String msg;

    public LoginException(String method) {
        /**
         * 存在异常处理类,无需存储状态信息
         */
        log.info("获取数据为空,接口:{}", method);
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
