package com.eudemon.taurus.app.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RedisTemplateDefaultImpl extends RedisTemplate {

	@Autowired
	private RedisPool redisPool;
	
	@Override
	protected RedisPool getRedisWritePool() {
		return redisPool;
	}

	@Override
	protected RedisPool getRedisReadPool() {
		return redisPool;
	}

}
