package com.eudemon.taurus.app.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public abstract class SpringJdbcBaseDao<T> {
	public abstract T rsToObj(ResultSet rs) throws SQLException;
	protected static Logger logger = Logger.getLogger("dberror");
	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(DataSource dynamicDataSource) {
		this.jdbcTemplate = new JdbcTemplate(dynamicDataSource);
	}

	/**
	 * get List result by sql
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryForList(String sql) {
		try {
			return (List<T>) jdbcTemplate.query(sql, new RowMapper<Object>() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rsToObj(rs);
				}
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * get list result by sql and param value
	 * 
	 * @param sql
	 * @param paramValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> queryForList(String sql, Object... paramValue) {
		try {
			return (List<T>) jdbcTemplate.query(sql, new RowMapper<Object>() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rsToObj(rs);
				}
			}, paramValue);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * get object result by sql
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryForObject(String sql) {
		try {
			return (T) jdbcTemplate.queryForObject(sql, new RowMapper<Object>() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rsToObj(rs);
				}
			});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * get object result by sql and param value
	 * 
	 * @param sql
	 * @param paramValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T queryForObject(String sql, Object... paramValue) {
		try {
			return (T) jdbcTemplate.queryForObject(sql, new RowMapper<Object>() {
				public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
					return rsToObj(rs);
				}
			}, paramValue);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * save by sql and param value
	 * 
	 * @param sql
	 * @param paramValue
	 * @return
	 */
	public boolean save(String sql, Object... paramValue) {
		int num = jdbcTemplate.update(sql, paramValue);

		if (num > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * save by sql, param type and param value, return the generated key
	 * 
	 * @param sql
	 * @param paramValue
	 * @return
	 * @throws DataAccessException
	 */
	public long saveOnGeneratedKey(final String sql, final Object... paramValue) throws DataAccessException {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				for (int i = 0; i < paramValue.length; i++) {
					ps.setObject(i + 1, paramValue[i]);
				}
				return ps;
			}
		}, keyHolder);

		Long id = (Long) keyHolder.getKeyList().get(0).get("GENERATED_KEY");

		return id;
	}
}
