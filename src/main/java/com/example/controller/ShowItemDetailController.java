package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.service.ShowItemDetailService;

@Controller
@RequestMapping("/")
public class ShowItemDetailController {
	
	@Autowired
	private ShowItemDetailService showItemDetailService;
	
	@GetMapping("/showItemDetail")
	public String showItemDetail(Integer id, Model model) {
		Item item = showItemDetailService.showItemDetail(id);
		model.addAttribute("item", item);
		return "detail";
	}

}
