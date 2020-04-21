package com.thornBird.springBootDemo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thornBird.springBootDemo.modules.account.service.UserService;

/**
 * @Description: 
 * @author: HymanHu
 * @date: 2020年4月8日
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 127.0.0.1:8085/account/login
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		userService.logout();
		return "redirect:/account/login";
	}
	
	/**
	 * 127.0.0.1:8085/account/dashboard
	 */
	@RequestMapping("/dashboard")
	public String dashboardPage() {
		return "index";
	}
	
	/**
	 * 127.0.0.1:8085/account/users
	 */
	@RequestMapping("/users")
	public String usersPage() {
		return "index";
	}
	
	/**
	 * 127.0.0.1:8085/account/roles
	 */
	@RequestMapping("/roles")
	public String rolesPage() {
		return "index";
	}
	
	/**
	 * 127.0.0.1:8085/account/resources
	 */
	@RequestMapping("/resources")
	public String resourcesPage() {
		return "index";
	}

}
