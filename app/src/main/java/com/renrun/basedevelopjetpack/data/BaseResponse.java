package com.renrun.basedevelopjetpack.data;

/**
 * Created by vence on 2018/4/27 17:48
 * 邮箱 ：vence0815@foxmail.com
 */
public class BaseResponse {

    // 返回状态 -1失败 1成功
    public static final String SUCCESS = "1";
    public static final String FAILED = "2";

    private String code = "";
    private String message = "";
    private String gohref = "";

    public boolean isResponseSuccess(){
        return SUCCESS.equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String r) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = message;
    }

    public String getGohref() {
        return gohref;
    }

    public void setGohref(String gohref) {
        this.gohref = gohref;
    }

}
