package com.xujinxin.datasync.exception;

import com.xujinxin.datasync.enums.ErrorType;

/**
 * 自定义异常类
 */
public class ValidException extends RuntimeException {

    private static final long serialVersionUID = 3284273643041868087L;

    public ValidException(ErrorType errorType) {
        super(errorType.getCode());
    }
}
