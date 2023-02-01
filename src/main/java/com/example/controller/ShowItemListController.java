package com.example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.SearchItemForm;
import com.example.repository.ItemsRepository;
import com.example.service.AddItemService;

/**
 * 商品一覧を操作するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/")
public class ShowItemListController {
	
	@Autowired
	private ItemsRepository itemsRepository;
	
	@Autowired
	private AddItemService addItemService;
	
	// 1ページの商品表示数 
	private final String LIMIT = "30";
	
	/** 商品一覧を表示する.
	 * @param model
	 * @param params
	 * @return 商品一覧
	 * @throws Exception
	 */
	@GetMapping("/showItemList")
	public String ShowItemList(Model model, @RequestParam HashMap<String, String> params, SearchItemForm form) throws Exception { 
		
		//大カテゴリーを取得
		List<Category> largeCategoryList = addItemService.findByLargeCategory();
		model.addAttribute("largeCategoryList", largeCategoryList);
		
		// @RequestParamでパラメータを設定し、現在のページを取得する  
		String currentPage = params.get("page");
		// 初期表示ではパラメータを取得できないので、1ページに設定
		if (currentPage == null) {
			currentPage = "1";
		}
		// データ取得時の取得件数、取得情報の指定
		HashMap<String, String> search = new HashMap<String, String>();
		search.put("limit", LIMIT);
		search.put("page", currentPage);
		
		Integer total = 0;
		List<Item> itemList = null;
		try {
			// データの総数を取得
			total = itemsRepository.getItemListCount();
			// データの一覧を取得
			itemList = itemsRepository.findAll(search);
		} catch (Exception e) {
			return "redirect:/list";
		}
		
		// ページング処理
        // "総数/1ページの表示数"から総ページ数を割り出す
		Integer totalPage = (total + Integer.valueOf(LIMIT) -1) / Integer.valueOf(LIMIT);
		Integer page = Integer.valueOf(currentPage);
		model.addAttribute("itemList", itemList);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		return "list";
	}
	
	/**
	 * ページ検索を行う.
	 * 
	 * @param model model
	 * @param params
	 * @param selectPage ページ数
	 * @return 商品一覧
	 * @throws Exception
	 */
	@PostMapping("/selectPage")
	public String selectPage(Model model, @RequestParam HashMap<String, String> params, String selectPage, SearchItemForm form) throws Exception {
		
		//データの総数を取得
		Integer total = itemsRepository.getItemListCount();
		// "総数/1ページの表示数"から総ページ数を割り出す
		Integer totalPage = (total + Integer.valueOf(LIMIT) -1) / Integer.valueOf(LIMIT);
		
		if (selectPage == null ) {
			selectPage = "1";
		}
		
		HashMap<String, String> search = new HashMap<>();
		Integer offSet = (Integer.valueOf(selectPage) -1) * Integer.valueOf(LIMIT);
		search.put("limit", LIMIT);
		search.put("page", String.valueOf(offSet));
		
		List<Item> itemList = itemsRepository.findAll(search);
		model.addAttribute("itemList", itemList);
		model.addAttribute("page", Integer.parseInt(selectPage));
		model.addAttribute("totalPage", totalPage);
		return "list";
	}
	
	
}
