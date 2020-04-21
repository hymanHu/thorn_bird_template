package com.thornBird.springBootDemo.modules.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.thornBird.springBootDemo.modules.test.entity.City;
import com.thornBird.springBootDemo.modules.test.entity.Country;
import com.thornBird.springBootDemo.modules.test.service.CountryService;

@RestController
public class CountryController {
	@Autowired
	private CountryService countryService;

	/**
	 * 127.0.0.1:8085/country/522
	 */
	@RequestMapping("/country/{countryId}")
	public Country getCountryById(@PathVariable int countryId) {
		return countryService.getCountryById(countryId);
	}
	
	/**
	 * 127.0.0.1:8085/cities/522
	 */
	@RequestMapping("/cities/{countryId}")
	public List<City> getCitiesByCountryId(@PathVariable int countryId) {
		return countryService.getCitiesByCountryId(countryId);
	}
	
	/**
	 * 127.0.0.1:8085/country?countryName=China
	 */
	@RequestMapping("/country")
	public Country getCountryByName(@RequestParam String countryName) {
		return countryService.getCountryByName(countryName);
	}
	
	/**
	 * 127.0.0.1:8085/cities?countryId=522&currentPage=1&pageSize=10
	 */
	@RequestMapping("/cities")
	PageInfo<City> getCitiesByPage(@RequestParam int countryId, 
			@RequestParam int currentPage, @RequestParam int pageSize) {
		return countryService.getCitiesByPage(countryId, currentPage, pageSize);
	}
	
	/**
	 * 127.0.0.1:8085/city
	 * {"cityName":"testName1","countryId":"522","dateCreated":"2019-08-27 14:38:39","localCityName":"bbb"}
	 */
	@PostMapping(value="/city", consumes="application/json")
	public City insertCity(@RequestBody City city) {
		countryService.insertCity(city);
		return city;
	}
	
	/**
	 * 127.0.0.1:8085/city
	 * form
	 */
	@PutMapping(value="/city", consumes="application/x-www-form-urlencoded")
	public City updateCity(@ModelAttribute City city) {
		countryService.updateCity(city);
		return city;
	}
	
	@DeleteMapping("/city/{cityId}")
	public void deleteCity(@PathVariable int cityId) {
		countryService.deleteCity(cityId);
	}
}
