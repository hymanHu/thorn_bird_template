package com.thornBird.scConsumer.modules.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/testDesc1")
	public String consumerDesc() {
		String temp = restTemplate.getForObject("http://PRODUCT/api/testDesc", String.class);
		return temp + "------------------";
	}
}
