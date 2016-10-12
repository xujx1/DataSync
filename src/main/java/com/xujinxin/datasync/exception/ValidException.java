package com.xujinxin.datasync.exception;

import com.xujinxin.datasync.enums.ErrorType;

/**
 * 自定义异常类
 */
public class ValidException extends RuntimeException {

    public ValidException(ErrorType errorType) {
        super(errorType.getCode());
    }
}
