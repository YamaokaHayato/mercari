package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.repository.CategoryRepository;
import com.example.repository.ItemsRepository;

@Service
@Transactional
public class AddItemService {
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Item addItem(Item item) {
		item =  itemsRepository.insert(item);
		return item;
	}
	
	public List<Category> findByLargeCategory() {
		List<Category> bigCategoryList = categoryRepository.findByLargeCategory();
		return bigCategoryList;
	}
	
	public List<Category> findByMediumCategory(Integer id) {
		List<Category> mediumCategoryList = categoryRepository.findByMidumCatgory(id);
		return mediumCategoryList;
	}
	
	public List<Category> findBySmallCategory(Integer id) {
		List<Category> smallCategoryList = categoryRepository.findBySmallCategory(id);
		return smallCategoryList;
	}

}
