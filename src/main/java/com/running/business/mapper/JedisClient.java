package com.running.business.mapper;


public interface JedisClient {
	public String get(String key);

	public String set(String key, String value);

	public String hget(String hkey, String key);

	public long hset(String hkey, String key, String value);

	public long incr(String key);

	public long expire(String key, int second);

	public long ttl(String key);

	public long del(String key) ;

	public long hdel(String hkey, String key);
	
	public boolean sismember(String key, String value);
	
	public long srem(String key, String value);
	
	public long scard(String key);
	
	public long sadd(String key, String value);
}
