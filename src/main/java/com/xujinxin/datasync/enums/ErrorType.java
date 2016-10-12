package com.xujinxin.datasync.enums;

/**
 * 异常信息类
 * 每种错误都包含两部分:int类型错误码,String类型错误描述
 */
public enum ErrorType {

    FAIL("FAIL", "操作失败"),

    SUCCESS("SUCCESS", "操作成功"),

    DATA_INVALID("DATA_INVALID", "数据无效"),

    USER_NOT_REGISTER("USER_NOT_REGISTER", "无权限访问");

    private String code;

    private String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessageByCode(String code) {
        String message = "";
        ErrorType[] errorTypes = ErrorType.values();
        for (ErrorType errorType : errorTypes) {
            if (errorType.getCode().equals(code)) {
                message = errorType.getMessage();
            }
        }
        return message;
    }
}
