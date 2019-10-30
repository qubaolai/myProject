package com.qubaolai.common.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoDataException extends RuntimeException{
    private Integer code;
    private String msg;

    public NoDataException(String method) {
        /**
         * 存在异常处理类,无需存储状态信息
         */
        log.info("获取数据为空,接口:{}", method);
        this.msg = method;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
