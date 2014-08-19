package com.eudemon.taurus.app.cache.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;

public abstract class RedisTemplate {
	private static Logger logger = Logger.getLogger(RedisTemplate.class);
	
	protected abstract RedisPool getRedisWritePool();

	protected abstract RedisPool getRedisReadPool();
	
	public byte[] get(byte[] key){
		Jedis jedis = getRedisReadPool().getJedis();
		
		byte[] rs = null;
		try {
			rs = jedis.get(key);
		} catch (Exception e) {
			logger.warn("redis get fail, byte key : " + new String(key), e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		
		getRedisReadPool().returnJedis(jedis);
		
		return rs;
	}
	
	public String get(String key){
		Jedis jedis = getRedisReadPool().getJedis();
		String rs = null;
		try {
			rs = jedis.get(key);
		} catch (Exception e) {
			logger.warn("redis get fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public List<byte[]> mget(byte[]... keys){
		Jedis jedis = getRedisReadPool().getJedis();
		List<byte[]> rs = null;
		try {
			rs = jedis.mget(keys);
		} catch (Exception e) {
			logger.warn("redis mget fail, byte key : " + keys, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public List<String> mget(String... keys){
		Jedis jedis = getRedisReadPool().getJedis();
		List<String> rs = null;
		try {
			rs = jedis.mget(keys);
		} catch (Exception e) {
			logger.warn("redis mget fail, byte key : " + keys, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public boolean set(byte[] key, byte[] value){
		Jedis jedis = getRedisWritePool().getJedis();
		String rs = null;
		try {
			rs = jedis.set(key, value);
		} catch (Exception e) {
			logger.warn("redis set fail, byte key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (null != rs && rs.equals("OK")) {
			return true;
		}
		return false;
	}
	
	public boolean set(String key, String value){
		Jedis jedis = getRedisWritePool().getJedis();
		String rs = null;
		try {
			rs = jedis.set(key, value);
		} catch (Exception e) {
			logger.warn("redis set fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (null != rs && rs.equals("OK")) {
			return true;
		}
		return false;
	}
	
	public boolean del(byte[] key){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.del(key);
		} catch (Exception e) {
			logger.warn("redis del fail, byte key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public boolean del(String key){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.del(key);
		} catch (Exception e) {
			logger.warn("redis del fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public long incr(byte[] key){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.incr(key);
		} catch (Exception e) {
			logger.warn("redis incr fail, byte key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);;
		}
		getRedisWritePool().returnJedis(jedis);
		return rs;
	}
	
	public long incr(String key){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.incr(key);
		} catch (Exception e) {
			logger.warn("redis incr fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);;
		}
		getRedisWritePool().returnJedis(jedis);
		return rs;
	}
	
	public long decr(byte[] key){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.decr(key);
		} catch (Exception e) {
			logger.warn("redis decr fail, byte key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);;
		}
		getRedisWritePool().returnJedis(jedis);
		return rs;
	}
	
	public long decr(String key){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.decr(key);
		} catch (Exception e) {
			logger.warn("redis decr fail, byte key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);;
		}
		getRedisWritePool().returnJedis(jedis);
		return rs;
	}
	
	public boolean expire(byte[] key, int seconds){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.warn("redis expire fail, byte key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public boolean expire(String key, int seconds){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.expire(key, seconds);
		} catch (Exception e) {
			logger.warn("redis expire fail, byte key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public boolean zadd(String key, long score, String member){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.zadd(key, score, member);
		} catch (Exception e) {
			logger.warn("redis zadd fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public Set<String> zrange(String key, long start, long end){
		Jedis jedis = getRedisReadPool().getJedis();
		Set<String> rs = null;
		try {
			rs = jedis.zrange(key, start, end);
		} catch (Exception e) {
			logger.warn("redis zrange fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public Set<String> zrevrange(String key, long start, long end){
		Jedis jedis = getRedisReadPool().getJedis();
		Set<String> rs = null;
		try {
			rs = jedis.zrevrange(key, start, end);
		} catch (Exception e) {
			logger.warn("redis zrevrange fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public long zcount(String key){
		Jedis jedis = getRedisReadPool().getJedis();
		long rs = 0;
		try {
			rs = jedis.zcard(key);
		} catch (Exception e) {
			logger.warn("redis zcount fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public boolean zrem(String key, String... members){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.zrem(key, members);
		} catch (Exception e) {
			logger.warn("redis zrem fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public boolean zremrangeByRank(String key, long start, long end){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.zremrangeByRank(key, start, end);
		} catch (Exception e) {
			logger.warn("redis zremrangeByRank fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public double zscore(String key, String member){
		Jedis jedis = getRedisReadPool().getJedis();
		double rs = 0;
		try {
			rs = jedis.zscore(key, member);
		} catch (Exception e) {
			logger.warn("redis zscore fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public Map<String, String> hgetAll(String key){
		Jedis jedis = getRedisReadPool().getJedis();
		Map<String, String> rs = null;
		try {
			rs = jedis.hgetAll(key);
		} catch (Exception e) {
			logger.warn("redis hgetAll fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public boolean hset(String key, String field, String value){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.warn("redis hset fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public boolean hdel(String key, String... fields){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.hdel(key, fields);
		} catch (Exception e) {
			logger.warn("redis hdel fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public long hlen(String key){
		Jedis jedis = getRedisReadPool().getJedis();
		long rs = 0;
		try {
			rs = jedis.hlen(key);
		} catch (Exception e) {
			logger.warn("redis hlen fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public boolean hexists(String key, String field){
		Jedis jedis = getRedisReadPool().getJedis();
		boolean rs = false;
		try {
			rs = jedis.hexists(key, field);
		} catch (Exception e) {
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public List<String> lrange(String key, long start, long end){
		Jedis jedis = getRedisReadPool().getJedis();
		List<String> rs = null;
		try {
			rs = jedis.lrange(key, start, end);
		} catch (Exception e) {
			logger.warn("redis lrange fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public boolean lpush(String key, String... value){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.lpush(key, value);
		} catch (Exception e) {
			logger.warn("redis lpush fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (rs == 1) {
			return true;
		}
		return false;
	}
	
	public long llen(String key){
		Jedis jedis = getRedisReadPool().getJedis();
		long rs = 0;
		try {
			rs = jedis.llen(key);
		} catch (Exception e) {
			logger.warn("redis llen fail, string key : " + key, e);
			getRedisReadPool().returnBrokenJedis(jedis);
		}
		getRedisReadPool().returnJedis(jedis);
		return rs;
	}
	
	public long lrem(String key, long count, String value){
		Jedis jedis = getRedisWritePool().getJedis();
		long rs = 0;
		try {
			rs = jedis.lrem(key, count, value);
		} catch (Exception e) {
			logger.warn("redis lrem fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		return rs;
	}
	
	public boolean ltrim(String key, long start, long end){
		Jedis jedis = getRedisWritePool().getJedis();
		String rs = null;
		try {
			rs = jedis.ltrim(key, start, end);
		} catch (Exception e) {
			logger.warn("redis ltrim fail, string key : " + key, e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		if (null != rs && rs.equals("OK")) {
			return true;
		}
		return false;
	}
	
	public String clean(){
		Jedis jedis = getRedisWritePool().getJedis();
		String rs = "";
		try {
			rs = jedis.flushAll();
		} catch (Exception e) {
			logger.warn("redis flush all fail", e);
			getRedisWritePool().returnBrokenJedis(jedis);
		}
		getRedisWritePool().returnJedis(jedis);
		return rs;
	}
}
