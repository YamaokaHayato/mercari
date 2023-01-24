package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * usersテーブルを操作するリポジトリ.
 * 
 * @author yamaokahayato
 *
 */
@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private static final RowMapper<User> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
	
	/**
	 * ユーザー情報をインサートする.
	 * 
	 * @param user user情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users (name, password, authority) VALUES (:username, :password, :authority);";
		template.update(sql, param);
	}
	
	/**
	 * ユーザー名からユーザー情報を取得.
	 * 
	 * @param userName　ユーザー名
	 * @return user情報
	 */
	public User load(String username) {
		String sql = "SELECT id, name, password, authority FROM users WHERE name =:name;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", username);
		User user = template.queryForObject(sql, param, USER_ROW_MAPPER);
		return user;
	}

}
