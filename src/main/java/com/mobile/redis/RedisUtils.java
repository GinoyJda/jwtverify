package com.mobile.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


     /**
      * 普通缓存获取
      * @param key 键
      * @return 值
     */
    public Object get(String key) {
        return  redisTemplate.opsForValue().get(key);
    }


    /**
     * 普通缓存放入
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 时效性key
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean setKeyTimeOut(String key, Object value) {
        int random=1;
        try {
            redisTemplate.opsForValue().set(key, value,random, TimeUnit.MINUTES);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * key是否存在
     */
    public boolean existsKey(String key){

        return redisTemplate.hasKey(key);


    }


    public boolean deleteKey(String key){

        return  redisTemplate.delete(key);

    }


}
