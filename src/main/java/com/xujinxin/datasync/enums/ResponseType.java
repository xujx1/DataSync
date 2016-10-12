package com.xujinxin.datasync.enums;

public enum ResponseType {

    SUCCESS("SUCCESS", "成功"),

    FAIL("FAIL", "失败");

    private String code = "";
    private String msg = "";


    ResponseType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

}
