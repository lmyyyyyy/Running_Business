package com.running.business.mapper;


import java.util.Set;

public interface JedisClient {
    String get(String key);

    String set(String key, String value);

    String hget(String hkey, String key);

    long hset(String hkey, String key, String value);

    long incr(String key);

    long expire(String key, int second);

    long ttl(String key);

    long del(String key);

    long hdel(String hkey, String key);

    boolean sismember(String key, String value);

    Set<String> smembers(String key);

    long srem(String key, String value);

    long srems(String key, String[] values);

    long scard(String key);

    long sadd(String key, String value);

    long sadds(String key, String[] values);
}
