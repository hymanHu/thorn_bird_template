package com.thornBird.sbd.modules.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping("/api/test/desc")
	@ResponseBody
	public String getAppDesc() {
		return "This is spring boot app.11111";
	}
}
