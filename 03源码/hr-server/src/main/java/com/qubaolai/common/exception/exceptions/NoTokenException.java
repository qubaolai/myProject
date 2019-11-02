package com.qubaolai.common.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoTokenException extends RuntimeException{
    private Integer code;
    private String msg;

    public NoTokenException(String method) {
        /**
         * 存在异常处理类,无需存储状态信息
         */
        log.info("获取数据为空,接口:{}", method);
    }
    public NoTokenException(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode(){
        return code;
    }
    @Override
    public String getMessage() {
        return msg;
    }
}
