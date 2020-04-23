package com.thornBird.sbd.modules.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thornBird.sbd.modules.test.entity.Country;
import com.thornBird.sbd.modules.test.service.CountryService;

@RestController
@RequestMapping("/api")
public class CountryController {
	
	@Autowired
	private CountryService countryService;

	/**
	 * http://127.0.0.1/api/country/522
	 */
	@RequestMapping("/country/{countryId}")
	public Country getCountryById(@PathVariable int countryId) {
		return countryService.getCountryById(countryId);
	}
	
	/**
	 * http://127.0.0.1/api/country?countryName=China
	 */
	@RequestMapping("/country")
	public Country getCountryByName(@RequestParam String countryName) {
		return countryService.getCountryByName(countryName);
	}
}
