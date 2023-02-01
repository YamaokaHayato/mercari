package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.EditItem;
import com.example.repository.EditItemRepository;

/**
 * ItemEditを操作するサービス.
 * 
 * @author yamaokahayato
 *
 */
@Service
@Transactional
public class ItemEditService {
	
	@Autowired
	private EditItemRepository editItemRepository;
	
	/**
	 * 商品情報を更新する.
	 * 
	 * @param item
	 */
	public void update(EditItem item) {
		editItemRepository.update(item);
	}
	
	/**
	 * 選択された商品を検索する.
	 * 
	 * @param id
	 * @return item
	 */
	public EditItem load(Integer id) {
		EditItem item = editItemRepository.load(id);
		return item;
	}

}
