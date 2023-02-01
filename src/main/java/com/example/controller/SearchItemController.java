package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.domain.SearchItem;
import com.example.form.SearchItemForm;
import com.example.repository.ItemsRepository;
import com.example.service.AddItemService;
import com.example.service.SearchItemService;

/**
 * 商品検索を行うコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/search")
public class SearchItemController {

	@Autowired
	private SearchItemService searchItemService;
	
	@Autowired 
	private ItemsRepository itemsRepository;

	@Autowired
	private AddItemService addItemService;

	private static final String LIMIT = "30";
	
	SearchItemForm SearchRecord;

	/**
	 * 商品検索を行う.
	 * 
	 * @param model
	 * @param form
	 * @return
	 */
	@PostMapping("/searchItem")
	public String searchItem(Model model, SearchItemForm form) {
		List<Category> largeCategoryList = addItemService.findByLargeCategory();
		model.addAttribute("largeCategoryList", largeCategoryList);
		SearchRecord = form;
		SearchItem searchItem = new SearchItem();
		BeanUtils.copyProperties(form, searchItem);
		List<Item> itemList = searchItemService.search(LIMIT, searchItem);
		model.addAttribute("itemList", itemList);
		return "searchItem";
	}

	/**
	 * 商品一覧を表示する.
	 * 
	 * @param model
	 * @param params
	 * @return 商品一覧
	 * @throws Exception
	 */
	@GetMapping("/searchItemAfterPage")
	public String searchItemAfterPage(Model model, @RequestParam HashMap<String, String> params,
			SearchItemForm itemSearchLog, SearchItemForm form) throws Exception {

		List<Category> largeCategoryList = addItemService.findByLargeCategory();
		model.addAttribute("largeCategoryList", largeCategoryList);

		// パラメータを設定し、現在のページを取得する
		String currentPage = params.get("page");
		System.out.println("currentPage : " + currentPage);
		// 初期表示ではパラメータを取得できないので、1ページに設定
		if (currentPage == null) {
			currentPage = "1";
		}

		// データ取得時の取得件数、取得情報の指定
		HashMap<String, String> search = new HashMap<String, String>();
		search.put("limit", LIMIT);
		search.put("page", currentPage);
		
		Integer page = Integer.valueOf(currentPage);
		Integer offset = Integer.parseInt(LIMIT) * Integer.parseInt(currentPage);
		// データの総数を取得
		Integer total = itemsRepository.getItemListCount();
		// "総数/1ページの表示数"から総ページ数を割り出す
		Integer totalPage = (total + Integer.valueOf(LIMIT) - 1) / Integer.valueOf(LIMIT);

		List<Item> itemList = searchItemService.searchItemAfterPage(LIMIT, offset, itemSearchLog);
		
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("page", page);
		model.addAttribute("itemList", itemList);
		return "searchItem";
	}

}
