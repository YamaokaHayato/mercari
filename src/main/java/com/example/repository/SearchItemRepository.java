package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

@Repository
public class SearchItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);
	
	public List<Item> findBySearchByName(String sql) {
		List<Item> searchItemList = template.query(sql, ITEM_ROW_MAPPER);
		return searchItemList;
	}

}
