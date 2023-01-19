package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemsRepository;

/**
 * 商品関連を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class ShowItemListService {
	
	@Autowired
	private ItemsRepository itemsRepository;
//	
//	public List<Item> showItem() {
//		List<Item> itemList = itemsRepository.findAll();
//		return itemList;
//	}
	
	public List<Item> showItemList(String name, Integer category, String brand){
		List<Item> itemList = itemsRepository.findByNameAndCategoryAndBrand(name, category, brand);
		return itemList;
	}

}
