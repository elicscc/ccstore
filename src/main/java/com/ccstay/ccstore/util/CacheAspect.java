package com.ccstay.ccstore.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component //交给spring管理
@Aspect //标识
public class CacheAspect {


    private JedisPool jedisPool = new JedisPool("127.0.0.1", 6379);
    private Jedis jedis = jedisPool.getResource();

    /**
     * 利用aop规则动态获取注解对象
     *
     * @param joinPoint
     * @param cacheFind
     * @return
     */
    @Around("@annotation(cacheFind)")
    public synchronized Object around(ProceedingJoinPoint joinPoint, Cache_Find cacheFind) {
        // System.out.println("aop启动了");
        String key = getKey(joinPoint, cacheFind);
        Object data = null;
        String re = jedis.get(key);
        try {
            if (StringUtils.isEmpty(re)) {
                data = joinPoint.proceed();
                String json = ObjectMapperUtil.toJson(data);
                if (cacheFind.secondes() > 0)
                    jedis.setex(key, cacheFind.secondes(), json);
                else jedis.set(key, json);
            } else {
                Class returnClass = (Class) getReturnClass(joinPoint);
                data = ObjectMapperUtil.toObject(re, returnClass);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return data;
    }

    private Object getReturnClass(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getReturnType();
    }

    private String getKey(ProceedingJoinPoint joinPoint, Cache_Find cacheFind) {
        String key = cacheFind.key();
        if (StringUtils.isEmpty(key)) {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            if (joinPoint.getArgs().length > 0)
                key = className + "." + methodName + "::" + joinPoint.getArgs()[0];
            else
                key = className + "." + methodName;

            return key;
        } else {
            return key;
        }
    }
}
