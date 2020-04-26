package com.thornBird.sbd.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestPageController {
	
	@RequestMapping("/index1")
	public String testIndexPage() {
		return "test/index1";
	}
	
	@RequestMapping("/index2")
	public String testIndexPage2() {
		return "test/index2";
	}
	
	@RequestMapping("/index3")
	public String testIndexPage3() {
		return "test/index3";
	}
	
	@RequestMapping("/index")
	public String testIndexPage(ModelMap modelMap) {
		modelMap.addAttribute("template", "test/index");
		return "index";
	}
}
