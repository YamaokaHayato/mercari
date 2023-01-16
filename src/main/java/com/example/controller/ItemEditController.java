package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Category;
import com.example.domain.EditItem;
import com.example.domain.Item;
import com.example.form.EditItemForm;
import com.example.service.AddItemService;
import com.example.service.ItemEditService;

/**
 * 商品編集を操作するコントローラー.
 * 
 * @author yamaokahayato
 *
 */
@Controller
@RequestMapping("/edit")
public class ItemEditController {
	
	@Autowired
	private ItemEditService itemEditService;
	
	@Autowired
	private AddItemService addItemService;
	
	@Autowired
	private ShowItemDetailController showItemDetailController;
	
	@GetMapping("")
	public String index(EditItemForm form, Model model, Integer id) {
		EditItem item = itemEditService.load(id);
		List<Category> largeCategoryList = addItemService.findByLargeCategory();
		
		form.setName(item.getName());
		form.setPrice(Double.toString(item.getPrice()));
		form.setCategory(Integer.toString(item.getCategory()));
		form.setBrand(item.getBrand());
		form.setCondition(Integer.toString(item.getCondition()));
		form.setDescription(item.getDescription());
		
		model.addAttribute("largeCategoryList", largeCategoryList);
		model.addAttribute("item", item);
		return "edit";
	}
	
	@PostMapping("/editItem")
	public String editItem(EditItemForm form, Model model, Integer id) {
		EditItem item = new EditItem();
		System.out.println(form);
		BeanUtils.copyProperties(form, item);
		item.setId(id);
		item.setPrice(Double.parseDouble(form.getPrice()));
		item.setCategory(Integer.parseInt(form.getCategory()));
		item.setCondition(Integer.parseInt(form.getCondition()));
		item.setShipping(0);
		System.out.println(item);
		itemEditService.update(item);
		return showItemDetailController.showItemDetail(id,model);
	}
	

}
