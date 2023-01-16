package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemsテーブルを操作するリポジトリ.
 * 
 * @author yamaokahayato
 *
 */
@Repository
public class ItemsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	/**
	 * item情報を取得する.
	 * 
	 * @return 商品リスト
	 */
	public List<Item> findAll() {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all as category, i.brand, i.price, i.shipping, i.description FROM items as i INNER JOIN category as c ON i.category = c.id ORDER BY id LIMIT 30;";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品情報を1件取得する.
	 * 
	 * @param id
	 * @return
	 */
	public Item load(Integer id) {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all as category, i.brand, i.price, i.shipping, i.description FROM items as i INNER JOIN category as c ON i.category = c.id where i.id =:i.id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("i.id", id);
		try {
			Item item = template.queryForObject(sql, param, ITEM_ROW_MAPPER);
			return item;
		} catch(Exception e) {
			return null;
		}
	}
	
	public Item insert(Item item) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		if(item.getId() == null) {
			String sql = "INSERT INTO items (name, condition, category, brand, price, shipping, description) VALUES (:name, :condition, :category, :brand, :price, :shipping, description);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = {"id"};
			template.update(sql, param, keyHolder, keyColumnNames);
			item.setId(keyHolder.getKey().intValue());
		} else {
			
		}
		return item;
	}
	
}
