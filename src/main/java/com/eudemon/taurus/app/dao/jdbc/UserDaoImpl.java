package com.eudemon.taurus.app.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.eudemon.taurus.app.common.Log;
import com.eudemon.taurus.app.dao.UserDao;
import com.eudemon.taurus.app.dao.common.SpringJdbcBaseDao;
import com.eudemon.taurus.app.entity.User;

@Repository
public class UserDaoImpl extends SpringJdbcBaseDao<User> implements UserDao {

	@Override
	public User rsToObj(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		user.setPasswordEncrypt(rs.getString("password_encrypt"));
		user.setSalt(rs.getString("salt"));
		user.setRoles(rs.getString("roles"));
		user.setPermissions(rs.getString("permissions"));
		user.setRegisterDate(rs.getTimestamp("register_date"));
		user.setPhoto(rs.getString("photo"));
		return user;
	}

	@Override
	public User query(long id) {
		String sql = "select * from user where id=?";
		return queryForObject(sql, id);
	}
	
	@Override
	public List<User> queryAll() {
		String sql = "select * from user";
		return queryForList(sql);
	}

	@Override
	public User queryByName(String userName) {
		String sql = "select * from user where name=?";
		return queryForObject(sql, userName);
	}
	
	@Override
	public List<User> queryListByScope(int start, int end) {
		String sql = "select * from user order by id asc limit ?,?";
		return queryForList(sql, start, end - start);
	}
	
	@Override
	public byte[] queryPhoto(final int id) {
		String sql = "select photo from user where id=" + id;
		
		List<byte[]> ls = jdbcTemplate.query(sql, new RowMapper<byte[]>(){
			@Override
			public byte[] mapRow(ResultSet rs, int rowNum) throws SQLException {
				InputStream is = rs.getBinaryStream("photo");
				if(null == is){
					return null;
				}
				try {
					byte[] picBt = IOUtils.toByteArray(is);
					return picBt;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			
		});
		
		if(ls != null && ls.size() > 0){
			return ls.get(0);
		}
		
		return null;
	}

	@Override
	public long save(final User user) {
		String sql = new String("insert into user(name, password, password_encrypt, salt, roles, permissions) values(?,?,?,?,?,?)");
		return saveOnGeneratedKey(sql, user.getName(), user.getPassword(), user.getPasswordEncrypt(), user.getSalt(), user.getRoles(), user.getPermissions());
	}
	
	@Override
	public boolean delete(long id) {
		String sql = "delete from user where id=?";
		return save(sql, id);
	}

	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void updatePhoto(final int id, final InputStream inputStream, final long size) {
		String sql = "update user set photo=? where id=?";
		try{
			this.jdbcTemplate.update(sql, new PreparedStatementSetter(){
				public void setValues(PreparedStatement pst) throws SQLException {
					pst.setBinaryStream(1, inputStream, size);
					pst.setInt(2, id);
				}
			});
		}catch(Exception e){
		    Log.getErrorLogger().error("update user photo fail, id:" + id, e);
		}finally{
		    
		}
	}
}
