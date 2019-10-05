package com.guanghua.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;

    public ResultVo() {
    }

    public ResultVo(Integer code, String msg, T t) {
        this.code = code;
        this.msg = msg;
        this.data = t;
    }

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultVo sendResult(Integer code, String msg, Object t) {
        return new ResultVo(code, msg, t);
    }

    public static ResultVo sendResult(Integer code, String msg) {
        return new ResultVo(code, msg);
    }
}
