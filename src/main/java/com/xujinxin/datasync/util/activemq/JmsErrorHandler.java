package com.xujinxin.datasync.util.activemq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ErrorHandler;

/**
 * 这是activemq自定义异常类
 */
public class JmsErrorHandler implements ErrorHandler {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void handleError(Throwable t) {
        LOGGER.error("[JMS Message Handle Error],{}", t.getMessage());
    }
}
