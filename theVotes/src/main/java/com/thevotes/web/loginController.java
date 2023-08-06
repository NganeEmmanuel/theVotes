package com.thevotes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.thevotes.domain.User;
import com.thevotes.service.UserService;

@Controller
public class loginController {

	@Autowired
	private UserService userService;


	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register") //for get request
	public String register(ModelMap model) {
		model.put("user", new User());
		return "register";
	}

	@PostMapping("/register") //for post request
	public String registerPost(@ModelAttribute User user) {
		User savedUser = userService.save(user);
		return "redirect:/login";
	}
}
