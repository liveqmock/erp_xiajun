package com.wangqin.globalshop.web.redis;

import com.wangqin.globalshop.web.TST;
import com.wangqin.globalshop.common.utils.LogWorker;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author pw
 * @date 2017-1-9
 */
@Slf4j
public class RedisTest extends TST {
    @Resource
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void redisTest() {
        redisTemplate.opsForList().leftPush("list",12232111);
        redisTemplate.opsForList().leftPush("list","32111");
        redisTemplate.opsForList().leftPush("list", true);
        redisTemplate.opsForList().rightPush("list", "饿");
        redisTemplate.opsForList().leftPush("list", 0);


        Object o1 = redisTemplate.opsForList().leftPop("list");
        Object o2 = redisTemplate.opsForList().rightPop("list");
        Object o3 = redisTemplate.opsForList().range("list",0,4);
        LogWorker.log(log,"o1",":{}",o1);
        LogWorker.log(log,"o2",":{}",o2);
        LogWorker.log(log,"o3",":{}",o3);
    }
}
