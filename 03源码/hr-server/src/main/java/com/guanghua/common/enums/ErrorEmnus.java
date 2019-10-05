package com.guanghua.common.enums;

public enum  ErrorEmnus {
    NODATA(400,"数据为空！");

    private Integer code;
    private String msg;

    ErrorEmnus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    /**
     * 根据code获取msg
     */
    public static String getMsg(Integer code) {
        ErrorEmnus[] values = values();
        for (ErrorEmnus message : values) {
            if (message.getCode().equals(code)) {
                return message.getMsg();
            }
        }
        return null;
    }
}
