package com.qubaolai.common.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description qubaolai
 * @Date 2020/1/18 15:13
 * @Description 数据异常的异常类
 */
@Slf4j
public class DataException  extends RuntimeException {
    private Integer code;
    private String msg;

    public DataException(String method){
        /**
         * 存在异常处理类,无需存储状态信息
         */
        log.info("获取数据为空,接口:{}", method);
    }
    public DataException(Integer code,String msg) {
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
