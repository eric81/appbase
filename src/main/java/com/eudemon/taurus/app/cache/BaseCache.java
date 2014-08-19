package com.eudemon.taurus.app.cache;


public interface BaseCache<T> {
	public T get(long id);

	public boolean add(T t);
	
	public boolean replace(T t);

	public boolean delete(long id);
	
	public boolean expire(long id, int seconds);
}
