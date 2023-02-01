package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

@Repository
public class CategoryRepository {
	
	@Autowired
	private  NamedParameterJdbcTemplate template;
	
	@Autowired
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(Category.class);
	
	public List<Category> findByLargeCategory() {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent is null AND name_all is null ORDER BY id;";
		List<Category> largeCategoryList = template.query(sql, CATEGORY_ROW_MAPPER);
		return largeCategoryList;
	}
	
	public List<Category> findByMidumCatgory(Integer id) {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent =:id AND name_all is null ORDER BY id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Category> mediumcategoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return mediumcategoryList;
	}
	
	public List<Category> findBySmallCategory(Integer id) {
		String sql = "SELECT id, parent, name, name_all FROM category WHERE parent =:id ORDER BY id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Category> smallCategoryList = template.query(sql, param, CATEGORY_ROW_MAPPER);
		return smallCategoryList;
	} 

}
 