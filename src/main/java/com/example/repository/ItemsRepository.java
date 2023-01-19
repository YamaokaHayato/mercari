package com.example.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
import com.example.domain.ItemCount;

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
	
	@Autowired
	private static final RowMapper<ItemCount> ITEMCOUNT_ROW_MAPPER = new BeanPropertyRowMapper<>(ItemCount.class);

	/**
	 * item情報を取得する.
	 * 
	 * @return 商品リスト
	 */
	public List<Item> findAll(HashMap<String, String> search) throws SQLException, IOException {
		String sql = "SELECT i.id, i.name, i.condition, c.name_all as category, i.brand, i.price, i.shipping, i.description FROM items as i "
				+ "INNER JOIN category as c ON i.category = c.id ORDER BY id limit :limit offset :offset;";
		System.out.println("limit : " + Integer.valueOf(search.get("limit")));
		Integer limit = Integer.valueOf(search.get("limit"));
		Integer page = Integer.valueOf(search.get("page")) - 1;
        // 何件情報を取得するかの指定と何件目からの情報を取得するかの指定（※コントローラからパラメータを使って現在のページ数が分かる。それによって何件目からの情報を取得すればいいのかが分かる。）
        SqlParameterSource param = new MapSqlParameterSource().addValue("limit", limit).addValue("offset", limit * page);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * Item数を取得する.
	 * 
	 * @return Item数
	 * @throws SQLException
	 * @throws IOException
	 */
	public Integer getItemListCount() throws SQLException, IOException {
		String sql = "select count(1) from items;";
		List<ItemCount> itemCount = template.query(sql, ITEMCOUNT_ROW_MAPPER);
		Integer count = itemCount.get(0).getCount();
		return count;
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
	
	public List<Item> findByNameAndCategoryAndBrand(String name,Integer category,String brand){
			String sql = "SELECT id,name,condition,category,brand,price,shipping,description FROM items " 
					+ "WHERE name LIKE :name AND category = :category AND brand LIKE :brand;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%").addValue("category", category).addValue("brand", "%" + brand + "%");
			List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
			return itemList;
	}
	
}
