package com.wangqin.globalshop.common.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.wangqin.globalshop.common.result.Cache;

public class RedisCacheTemplate implements Cache, InitializingBean {

    private String                prefix;    // key的前缀，加前缀是为了防止key的重复或者生成新的缓存，该值在整个应用里面不能重复
    private long                  expiry;    // 缓存过期时间(秒)

    RedisTemplate<String, Object> cacheRedis;

    public void afterPropertiesSet() throws Exception {
        if (cacheRedis == null) {
            throw new Exception("\"cacheRedis\" must be configurated");
        }
        if (expiry == 0) {
            throw new Exception("\"expiry\" must be configurated or cannot be zero");
        }

    }

    public Object get(final String key) {
        Object result = cacheRedis.execute(new RedisCallback<Object>() {

            @SuppressWarnings({ "rawtypes", "unchecked" })
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = cacheRedis.getDefaultSerializer();
                byte[] keyBytes = serializer.serialize(rebuildKey(key));
                byte[] valueBytes = connection.get(keyBytes);
                if (valueBytes == null) {
                    return null;
                }
                Object object = serializer.deserialize(valueBytes);
                return object;
            }
        });
        return result;
    }

    public boolean put(final String key, final Object value) {
        boolean result = cacheRedis.execute(new RedisCallback<Boolean>() {

            @SuppressWarnings({ "rawtypes", "unchecked" })
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = cacheRedis.getDefaultSerializer();
                byte[] keyBytes = serializer.serialize(rebuildKey(key));
                byte[] valueBytes = serializer.serialize(value);
                connection.setEx(keyBytes, expiry, valueBytes);
                return true;
            }
        });
        return result;
    }

    public void remove(String key) {
        cacheRedis.delete(rebuildKey(key));
    }

    @Override
    public Long incr(final String key) {
        Long result = cacheRedis.execute(new RedisCallback<Long>() {

            @SuppressWarnings({ "rawtypes", "unchecked" })
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer serializer = cacheRedis.getDefaultSerializer();
                byte[] keyBytes = serializer.serialize(rebuildKey(key));
                Long resultVal = connection.incr(keyBytes);
                return resultVal;
            }
        });
        return result;
    }

    /**
     * 重新生成key，加入前缀
     * 
     * @param key
     * @return
     */
    private String rebuildKey(String key) {
        return prefix + "_" + key;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setCacheRedis(RedisTemplate cacheRedis) {
        this.cacheRedis = cacheRedis;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

}
