package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemsRepository;

/**
 * 商品詳細情報を操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	/**
	 * 1件の商品情報を取得する
	 * 
	 * @param id
	 * @return item
	 */
	public Item showItemDetail(Integer id) {
		Item item = itemsRepository.load(id);
		return item;
	}

}
