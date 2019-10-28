package com.guanghua.common.exception.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * @author qubaolai
 * @date 2019/10/27 19:12
 */
@Slf4j
public class UserException extends RuntimeException{
    private Integer code;
    private String msg;

    public UserException(String method){
        log.info("用户异常！", method);
    }

    @Override
    public String getMessage(){
        return msg;
    }
}
