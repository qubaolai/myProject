package com.qubaolai.common.exception.exceptions;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class NoLoginException extends RuntimeException{
    private Integer code;
    private String msg;

    public NoLoginException(String method) {
        /**
         * 存在异常处理类,无需存储状态信息
         */
        log.info("获取数据为空,接口:{}", method);
    }
    public NoLoginException(Integer code,String msg) {
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
