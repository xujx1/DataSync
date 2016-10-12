package com.xujinxin.datasync.aspect;

import com.xujinxin.datasync.bean.User;
import com.xujinxin.datasync.util.redis.RedisOperationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 使用切面来将数据库查出来的数据
 * 保存进redis
 */
@Aspect
@Component
public class UserDataLogAspect {

    @Autowired
    private RedisOperationService redisOperationService;

    @AfterReturning(value = "execution(public * com.xujinxin.datasync.service.impl.*.selectUserById(..)))", returning = "returnVal")
    public void setUserData2Cache(JoinPoint joinPoint, User returnVal) {
        String id = String.valueOf(joinPoint.getArgs()[0]);
        redisOperationService.setCache(id, returnVal);
    }
}
