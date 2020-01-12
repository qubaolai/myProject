package com.qubaolai.common.enums;

public enum  ErrorEmnus {
    NODATA(400,"数据为空！"),
    NOTOKEN(201,"无token，请重新登录！"),
    TOKENMISSING(202,"token信息缺失，请重新登录"),
    NOUSER(203,"用户不存在！"),
    USERINFOERROR(204,"用户名密码错误"),
    PASSWORDERROR(205,"新密码与旧密码相同！"),
    PASSWORDLOGERROR3(206,"密码错误超过三次"),
    COFIRMPASSWORDERROR(207,"新密码与确认密码不同！"),
    PASSWORDLOGERROR5(208,"密码错误超过五次,今日不能登录!"),
    PARAMERROR(209,"请求参数异常！");

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
