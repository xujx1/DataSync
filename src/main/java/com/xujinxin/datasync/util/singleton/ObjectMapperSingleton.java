package com.xujinxin.datasync.util.singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 使用枚举实现单例
 */
public enum ObjectMapperSingleton {
    INSTANCE;

    private ObjectMapper objectMapper;

    ObjectMapperSingleton() {
        objectMapper = Jackson2ObjectMapperBuilder.json().build();
    }

    public ObjectMapper getInstance() {
        return objectMapper;
    }
}
