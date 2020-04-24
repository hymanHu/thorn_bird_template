package com.thornBird.sbd.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.test.entity.City;

public interface CityService {

	List<City> getCitiesByCountryId(int countryId);
	
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);
	
	City getCityByName(String cityName, String localCityName);
	
	Result<City> insertCity(City city);
	
	Result<City> updateCity(City city);
	
	Result<Object> deleteCity(int cityId);
}
