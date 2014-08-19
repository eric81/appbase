package com.eudemon.taurus.app.dao;

import java.io.InputStream;
import java.util.List;

import com.eudemon.taurus.app.entity.User;

public interface UserDao extends BaseDao<User>{
	public List<User> queryAll();

	public User queryByName(String userName);

	public List<User> queryListByScope(int start, int end);
	
	public byte[] queryPhoto(int id);

	public void updatePhoto(int id, InputStream inputStream, long size);


}
