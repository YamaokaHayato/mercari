package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.EditItem;

@Repository
public class EditItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private static final RowMapper<EditItem> EDITITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(EditItem.class);
	
	public EditItem load(Integer id) {
		String sql = "SELECT id, name, condition, category, brand, price, shipping, description FROM items WHERE id =:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		try {
			EditItem item = template.queryForObject(sql, param, EDITITEM_ROW_MAPPER);
			return item;
		} catch(Exception e) {
			return null;
		}
	}
	
	public void update(EditItem item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		String sql = "UPDATE items SET name =:name, condition =:condition, category =:category, brand =:brand, price =:price, shipping =:shipping, description =:description where id =:id;";
		template.update(sql, param);
	}

}
