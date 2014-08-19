package com.eudemon.taurus.app.dao;

public interface BaseDao<T> {
	public T query(long id);
	public long save(T t);
	public boolean update(T t);
	public boolean delete(long id);
}
