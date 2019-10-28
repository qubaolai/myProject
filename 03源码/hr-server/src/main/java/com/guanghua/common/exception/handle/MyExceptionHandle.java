package com.guanghua.common.exception.handle;

import com.guanghua.common.exception.exceptions.NoDataException;
import com.guanghua.common.exception.exceptions.NoLoginException;
import com.guanghua.common.exception.exceptions.NoTokenException;
import com.guanghua.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
}
