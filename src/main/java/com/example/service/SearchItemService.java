package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.SearchItem;
import com.example.form.SearchItemForm;
import com.example.repository.ItemsRepository;
import com.example.repository.SearchItemRepository;


@Service
@Transactional
public class SearchItemService {
	
	@Autowired
	private SearchItemRepository searchItemRepository;
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	
	public List<Item> search(String LIMIT, SearchItem searchItem) {
		List<Item> itemList = searchItemRepository.search(LIMIT, searchItem);
		return itemList;
	}

	public List<Item> searchItemAfterPage(String LIMIT, Integer offSet, SearchItemForm searchRecord) {
		List<Item> itemList = searchItemRepository.searchItemAfterPage(LIMIT, offSet, searchRecord);
		return itemList;
	}
	
}
