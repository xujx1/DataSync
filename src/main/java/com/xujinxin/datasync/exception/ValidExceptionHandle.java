package com.xujinxin.datasync.exception;

import com.xujinxin.datasync.enums.ErrorType;
import com.xujinxin.datasync.enums.ResponseType;
import com.xujinxin.datasync.vo.ResponseVo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ControllerAdvice
public class ValidExceptionHandle {

    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ResponseVo> handle(Exception e) {
        String message = e instanceof ValidException ? ErrorType.getMessageByCode(e.getMessage()) : e.getMessage();
        LOGGER.error(e);
        return new ResponseEntity<ResponseVo>(new ResponseVo.Builder().success(false).msg(ResponseType.FAIL.getCode()).data(message).build(), HttpStatus.OK);
    }
}
