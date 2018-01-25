package com.running.business.mapper.impl;

import com.running.business.mapper.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class JedisClientSingle implements JedisClient {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 获取redis中的key的值
     */
    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.get(key);
        jedis.close();
        return str;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.set(key, value);
        jedis.close();
        return str;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String str = jedis.hget(hkey, key);
        jedis.close();
        return str;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }

    /**
     * 判断key，value是否在集合中
     */
    @Override
    public boolean sismember(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        boolean result = jedis.sismember(key, value);
        jedis.close();
        return result;
    }

    /**
     * 获取集合内所有成员
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        Jedis jedis = jedisPool.getResource();
        Set<String> result = jedis.smembers(key);
        jedis.close();
        return result;
    }

    /**
     * 移除key中的value
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public long srem(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.srem(key, value);
        jedis.close();
        return result;
    }

    /**
     * 移除key中的多个value
     *
     * @param key
     * @param values
     * @return
     */
    @Override
    public long srems(String key, String[] values) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.srem(key, values);
        jedis.close();
        return result;
    }

    /**
     * 获取集合大小
     *
     * @param key
     * @return
     */
    @Override
    public long scard(String key) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.scard(key);
        jedis.close();
        return result;
    }


    /**
     * 将value添加到key集合中
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public long sadd(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.sadd(key, value);
        jedis.close();
        return result;
    }

    /**
     * 将多个value添加到key集合中
     *
     * @param key
     * @param values
     * @return
     */
    @Override
    public long sadds(String key, String[] values) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.sadd(key, values);
        jedis.close();
        return result;
    }

}
