package com.running.business.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.running.business.mapper.JedisClient;

public class JedisClientSingle implements JedisClient{

	@Autowired
	private JedisPool jedisPool;
	
	/**
	 * 获取redis中的key的值
	 */
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.get(key);
		jedis.close();
		return str;
	}

	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String  str = jedis.set(key, value);
		jedis.close();
		return str;
	}

	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String str = jedis.hget(hkey, key);
		jedis.close();
		return str;
	}

	
	public long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hset(hkey, key, value);
		jedis.close();
		return result;
	}

	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	public long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}

	public long ttl(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.ttl(key);
		jedis.close();
		return result;
	}

	public long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	public long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

	/**
	 * 判断key，value是否在集合中
	 */
	public boolean sismember(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		boolean result = jedis.sismember(key, value); 
		jedis.close();
		return result;
	}

	/**
	 * 取消点赞
	 */
	public long srem(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.srem(key, value);
		jedis.close();
		return result;
	}

	/**
	 * 获取集合大小
	 */
	public long scard(String key) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.scard(key);
		jedis.close();
		return result;
	}

	/**
	 * 点赞
	 */
	public long sadd(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		long result = jedis.sadd(key, value);
		jedis.close();
		return result;
	}

}
