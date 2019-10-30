package com.qubaolai.common.enums;

public enum  ErrorEmnus {
    NODATA(400,"数据为空！"),
    NOTOKEN(201,"无token，请重新登录！"),
    TOKENMISSING(202,"token信息缺失，请重新登录"),
    NOUSER(203,"用户不存在！"),
    USERINFOERROR(204,"用户名密码错误，请重新登录");

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
