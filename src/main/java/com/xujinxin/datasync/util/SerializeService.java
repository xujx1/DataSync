package com.xujinxin.datasync.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xujinxin.datasync.util.singleton.ObjectMapperSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SerializeService {

    private static final Logger LOGGER = LogManager.getLogger();
    private ObjectMapper objectMapper = ObjectMapperSingleton.INSTANCE.getInstance();

    /**
     * 反序列化
     * json转换为对象
     */
    public <T> T deSerialize(String json, Class<T> classType) {
        try {
            return objectMapper.readValue(json, classType);
        } catch (IOException e) {
            LOGGER.error("Json to Object Error,{}", e);
            return null;
        }
    }

    /**
     * 序列化
     * 直接转为json
     */
    public String serialize(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            LOGGER.error("Object to Json Error,{}", e);
            return "";
        }
    }
}
