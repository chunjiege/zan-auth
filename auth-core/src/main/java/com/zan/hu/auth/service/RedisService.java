package com.zan.hu.auth.service;

/**
 * @version 1.0
 * @Author hupeng
 * @Date 2019-04-09 22:53
 * @Description todo
 **/
public interface RedisService {

    Object get(String key);

    void set(String key, Object value);

    void set(String key, Object value, long time);

    void remove(String ... key);
}
