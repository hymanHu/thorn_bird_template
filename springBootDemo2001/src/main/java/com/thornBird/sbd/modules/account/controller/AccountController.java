package com.thornBird.sbd.modules.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: account 模块modelandview控制器，返回页面，尽量不要涉及数据处理
 * @author: HymanHu
 * @date: 2020年4月29日
 */
@Controller
@RequestMapping("/account")
public class AccountController {

	/**
	 * http://127.0.0.1/account/login
	 */
	@RequestMapping("/login")
	public String loginPage() {
		return "indexSimple";
	}
	
	/**
	 * http://127.0.0.1/account/register
	 */
	@RequestMapping("/register")
	public String registerPage() {
		return "indexSimple";
	}
	
	/**
	 * http://127.0.0.1/account/users
	 */
	@RequestMapping("/users")
	public String usersPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/roles
	 */
	@RequestMapping("/roles")
	public String rolesPage() {
		return "index";
	}
	
	/**
	 * http://127.0.0.1/account/resources
	 */
	@RequestMapping("/resources")
	public String resourcesPage() {
		return "index";
	}
}
