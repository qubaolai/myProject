package com.qubaolai.common.exception.handle;

import com.qubaolai.common.exception.exceptions.*;
import com.qubaolai.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MyExceptionHandle {
    @ExceptionHandler(RuntimeException.class)
    public ResultVo runtionExceptionHandle(RuntimeException e) {
        log.error("请求发生异常:{}", e.getMessage(), e);
        return ResultVo.sendResult(500, e.getMessage());
    }
    /**
     * 对自定义异常的统一处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoLoginException.class)
    public ResultVo noLonginExceptionHandle(NoLoginException e) {
        log.error(e.getMessage());
        return ResultVo.sendResult(e.getCode(), e.getMsg());
    }
    @ExceptionHandler(NoTokenException.class)
    public ResultVo noTokenExceptionHandle(NoTokenException e) {
        log.error(e.getMessage());
        return ResultVo.sendResult(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(NoDataException.class)
    public ResultVo noDataExceptionHandle(NoDataException e) {
        log.error(e.getMessage());
        return ResultVo.sendResult(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(ParamException.class)
    public ResultVo paramExceptionHandle(ParamException e) {
        log.error(e.getMessage());
        return ResultVo.sendResult(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(LoginException.class)
    public ResultVo LoginExceptionHandle(LoginException e) {
        log.error(e.getMessage());
        return ResultVo.sendResult(e.getCode(), e.getMessage());
    }
    @ExceptionHandler(DataException.class)
    public ResultVo DataExceptionHandle(DataException e) {
        log.error(e.getMessage());
        return ResultVo.sendResult(e.getCode(), e.getMessage());
    }
}
