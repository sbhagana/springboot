package com.ss.easie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	
	@RequestMapping(value = { "/login", "/" })
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/home")
	public String homeView() {
		return "home";
	}

	@RequestMapping(value = "/logout")
	public String logoutView() {
		return "redirect:login";
	}

}
