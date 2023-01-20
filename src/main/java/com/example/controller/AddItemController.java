package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Category;
import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.service.AddItemService;

/**
 * 商品追加を操作するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/add")
public class AddItemController {

	@Autowired
	private AddItemService addItemService;

	/**
	 * 商品追加ページを表示.
	 * 
	 * @param form ItemForm
	 * @param model model
	 * @return 商品追加ページ
	 */
	@GetMapping("")
	public String index(ItemForm form, Model model) {
		List<Category> largeCategoryList = addItemService.findByLargeCategory();
		model.addAttribute("largeCategoryList", largeCategoryList);
		return "add";
	}

	@PostMapping("/addItem")
	public String addItem(@Validated ItemForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return index(form, model);
		}

		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(Double.parseDouble(form.getPrice()));
		item.setCategory(form.getCategory());
		item.setCondition(Integer.parseInt(form.getCondition()));
		item.setDescription(form.getDescription());
		item.setShipping(0);
		addItemService.addItem(item);
		return "redirect:/add";
	}

	@ResponseBody
	@PostMapping("/mediumCategory")
	public List<Category> addMediumCategory(Integer id, Model model) {
		List<Category> mediumCategoryList = addItemService.findByMediumCategory(id);
		return mediumCategoryList;
	}

	@ResponseBody
	@PostMapping("/smallCategory")
	public List<Category> addSmallCategory(Integer id, Model model) {
		List<Category> smallCategoryList = addItemService.findBySmallCategory(id);
		return smallCategoryList;
	}

}
