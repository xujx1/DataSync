package com.xujinxin.datasync.scheduled;

import com.xujinxin.datasync.util.redis.RedisOperationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 这是处理User缓存的定时任务
 * 每天的0点，请求redis缓存
 */
@Component
public class UserCacheOperationScheduled {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private RedisOperationService redisOperationService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void test() {
        LOGGER.info("执行定时任务，清除缓存");
        redisOperationService.clear();
    }
}
