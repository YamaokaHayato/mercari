package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserLoginForm;
import com.example.service.UserLoginService;

@Controller
@RequestMapping("/userLogin")
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("")
	public String toLogin(UserLoginForm form) {
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(UserLoginForm form) {
//		User user = (User) userLoginService.loadUserByUsername(form.getUsername());
//		session.setAttribute("user", user);
		return "redirect:/showItemList";
	}

}
