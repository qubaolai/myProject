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
        ResultVo.sendResult(400, e.getMsg());
        return ResultVo.sendResult(400, e.getMsg());
    }
    @ExceptionHandler(NoTokenException.class)
    public ResultVo noLonginExceptionHandle(NoTokenException e) {
        ResultVo.sendResult(400, e.getMessage());
        return ResultVo.sendResult(400, e.getMessage());
    }
    @ExceptionHandler(NoDataException.class)
    public ResultVo noLonginExceptionHandle(NoDataException e) {
        ResultVo.sendResult(400, e.getMessage());
        return ResultVo.sendResult(400, e.getMessage());
    }
    @ExceptionHandler(UserException.class)
    public ResultVo userExceptionHandle(UserException e){
        return ResultVo.sendResult(400,e.getMessage());
    }
    @ExceptionHandler(ParamException.class)
    public ResultVo paramExceptionHandle(ParamException e){
        return ResultVo.sendResult(400, e.getMessage());
    }
}
