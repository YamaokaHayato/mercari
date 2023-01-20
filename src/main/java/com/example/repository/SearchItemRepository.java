package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.SearchItem;
import com.example.form.SearchItemForm;

@Repository
public class SearchItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
	
	/**
	 * 商品の検索をするメソッド.
	 * 
	 * @param 
	 * @return　itemList
	 */
	public List<Item> search(String LIMIT, SearchItem searchItem) {
		String sql = 
				"SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items as i INNER JOIN category as c ON i.category = c.id ";
		// categoryがない場合の検索
		if (searchItem.getSmallCategory() == 0) {
			sql += " AND i.name ILIKE" + "'%" + searchItem.getName() + "%'" + " AND i.brand ILIKE '%"
					+ searchItem.getBrand() + "%'";
		}
		// categoryがある場合の検索
		if (searchItem.getSmallCategory() != 0) {
			sql += " AND i.name ILIKE" + "'%" + searchItem.getName() + "%'" + " AND i.brand ILIKE '%"
					+ searchItem.getBrand() + "%'" + " AND i.category = " + searchItem.getSmallCategory();
		}
		sql += " ORDER BY i.id LIMIT " + Integer.parseInt(LIMIT) + ";";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * ページング後に商品を検索するメソッド.
	 * 
	 * @param 
	 * @return itemList一覧
	 */
	public List<Item> searchItemAfterPage(String LIMIT, Integer offSet, SearchItemForm searchRecord) {
		String sql = 
				"SELECT i.id, i.name, i.condition, c.name_all AS category, i.brand, i.price, i.shipping, i.description FROM items i INNER JOIN category c ON i.category = c.id;";
		// categoryがない場合の検索
		if (searchRecord.getSmallCategory() == 0) {
			sql += " AND i.name ILIKE" + "'%" + searchRecord.getName() + "%'" + " AND i.brand ILIKE '%"
					+ searchRecord.getBrand() + "%'";
		}
		// categoryがある場合の検索
		if (searchRecord.getSmallCategory() != 0) {
			sql += " AND i.name ILIKE" + "'%" + searchRecord.getName() + "%'" + " AND i.brand ILIKE '%"
					+ searchRecord.getBrand() + "%'" + " AND i.category = "
					+ searchRecord.getSmallCategory();
		}
		sql += "ORDER BY i.id LIMIT " + Integer.parseInt(LIMIT) + "OFFSET " + offSet + ";";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}

}
