package com.thornBird.sbd.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.test.entity.City;

public interface CityService {

	List<City> getCitiesByCountryId(int countryId);
	
	PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId);
	
	Result<City> insertCity(City city);
}
