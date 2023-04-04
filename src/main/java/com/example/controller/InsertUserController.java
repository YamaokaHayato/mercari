package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.InsertUserForm;
import com.example.service.UserService;

@Controller
@RequestMapping("/user")
public class InsertUserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/toInsert")
	public String toInsert(InsertUserForm form, Model model) {
		return "register";
		
	}
	
	@PostMapping("/insert")
	public String insert(@Validated InsertUserForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return toInsert(form, model);
		}
		User user = new User();
		user.setAuthority(null);
		BeanUtils.copyProperties(form, user);
		userService.insert(user);
		
		return "redirect:/login";
	}
	
	

}
