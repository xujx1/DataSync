package com.xujinxin.datasync.util.activemq;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.xujinxin.datasync.bean.User;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;


public class ValidMessageConverter extends MappingJackson2MessageConverter {

    @Override
    protected JavaType getJavaTypeForMessage(Message message) throws JMSException {
        return TypeFactory.defaultInstance().constructType(User.class);
    }
}
