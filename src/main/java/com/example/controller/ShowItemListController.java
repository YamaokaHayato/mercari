package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemListService;

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
	private ShowItemListService showItemListService;
	
	@GetMapping("/showItemList")
	public String ShowItemList(Model model) {
		List<Item> itemList = showItemListService.showItem();
		model.addAttribute("itemList", itemList);
		return "list";
	}
	
}
