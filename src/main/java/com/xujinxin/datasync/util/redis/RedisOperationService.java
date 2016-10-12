package com.xujinxin.datasync.util.redis;

import com.xujinxin.datasync.util.SerializeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 这是redis的集中处理方法类
 */
@Service
@PropertySource(value = "classpath:properties/redis.properties")
public class RedisOperationService {

    private static final Logger LOGGER = LogManager.getLogger();
    //设置默认缓存时间：5分钟
    private final int time = 300;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SerializeService serializeService;

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.database}")
    private String database;

    /**
     * 检查是否连接成功
     */
    public Boolean ping() {
        Jedis jedis = new Jedis(host, port);
        String str = "";
        try {
            str = jedis.ping();
        } catch (Exception e) {
            LOGGER.error("连接redis异常,{}", ExceptionUtils.getMessage(e));
        }
        return "PONG".equals(str);
    }

    /**
     * 设置缓存,默认设置5分钟
     */
    public void setCache(String key, Object value) {
        setCache(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 先判断是否连接成功
     * 其实这边应该考虑的是重写
     */
    public void setCache(String key, Object value, int time, TimeUnit timeUnit) {
        if (StringUtils.isNotEmpty(key) && null != value && ping()) {
            time = 0 == time ? this.time : time;
            timeUnit = null == timeUnit ? TimeUnit.SECONDS : timeUnit;
            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(key, serializeService.serialize(value), time, timeUnit);
        }
    }

    /**
     * 获取缓存
     */
    public <T> T getCache(String key, Class<T> clazz) {
        T t = null;
        if (StringUtils.isNotEmpty(key) && null != clazz && ping()) {
            String json = redisTemplate.opsForValue().get(key);
            if (StringUtils.isNotEmpty(json)) {
                t = serializeService.deSerialize(json, clazz);
            }
        }
        return t;
    }

    /**
     * 设置定时清除对应的redis缓存
     */
    public void clear() {
        if (ping()) {
            Set<String> keys = redisTemplate.opsForValue().getOperations().keys("*");
            if (null != keys) {
                for (String key : keys) {
                    redisTemplate.delete(key);
                }
            }
        }
    }
}
