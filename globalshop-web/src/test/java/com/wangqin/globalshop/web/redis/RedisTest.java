package com.wangqin.globalshop.web.redis;

import com.alibaba.fastjson.JSON;
import com.wangqin.globalshop.biz1.app.dal.dataObject.WarehouseDO;
import com.wangqin.globalshop.web.TST;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;

/**
 * @author pw
 * @date 2017-1-9
 */
public class RedisTest extends TST {
//    @Resource
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
    }


}
