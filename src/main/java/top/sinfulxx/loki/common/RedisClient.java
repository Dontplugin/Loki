package top.sinfulxx.loki.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * redis包装工具类
 * 对于redisTpl.opsForValue().set(key, value)进行了一次封装，不然每次都要这样保存值
 * 而封装后只需：new RedisClient().set(key,value);
 */
@Component
public class RedisClient {


    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存值到缓存里面
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    /**
     * 通过key获取缓存里对应的value
     * @param key
     * @return
     */
    public String get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
