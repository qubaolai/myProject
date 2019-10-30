package com.qubaolai.common.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qubaolai
 * @date 2019/10/27 19:04
 */
@Slf4j
public class ParamException extends RuntimeException{
    private Integer code;
    private String msg;

    public ParamException(String method) {
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
