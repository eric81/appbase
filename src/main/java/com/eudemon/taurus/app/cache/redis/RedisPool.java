package com.eudemon.taurus.app.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	@Autowired
	private JedisPoolConfig config;
	
	private String host = "";
	private int port = 0;
	private String password = "";
	private int timeout = 0;

	private JedisPool pool;

	public void init() {
		if (pool == null) {
			pool = new JedisPool(config, host, port, timeout, password);
		}
	}
	
	public Jedis getJedis(){
		return pool.getResource();
	}
	
	public void returnJedis(Jedis jedis){
		pool.returnResource(jedis);
	}
	
	public void returnBrokenJedis(Jedis jedis){
		pool.returnBrokenResource(jedis);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
