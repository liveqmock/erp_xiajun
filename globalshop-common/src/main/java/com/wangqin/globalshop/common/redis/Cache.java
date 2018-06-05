package com.wangqin.globalshop.common.redis;

/**
 * 
 * 类Cache.java的实现描述：TODO redis Cache
 * @author sivanblack 2018年5月25日 下午5:55:01
 */
public interface Cache {

    /**
     * 从缓存获取单条数据
     * 
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 向缓存put数据
     * 
     * @param key
     * @param value
     * @return
     */
    boolean put(String key, Object value);

    /**
     * 向缓存put临时数据
     *
     * @param key
     * @param value
     * @return
     */
    boolean putEx(String key, Object value, long expiry);


    /**
     * remove数据
     * 
     * @param key
     * @return
     */
    void remove(String key);

    /**
     * 计数
     * 
     * @param key
     * @return
     */
    Long incr(String key);

}
