package com.zan.hu.auth.service;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-09 22:53
 * @Description todo
 **/
public interface RedisService {

    /***
     * key
     * @param key
     * @return
     */

    Object get(String key);

    void set(String key, Object value);

    void set(String key, long time, Object value);

    void remove(String... key);

    /**
     * set 集合
     */

    void add(String key, Object... values);

    void add(String key, long time, Object... values);

    void setRemove(String key, String value);

    //指定缓存失效的时间
    boolean expire(String key, long time);
}
