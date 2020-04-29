package com.thornBird.sbd.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.common.vo.Result.ResultStatus;
import com.thornBird.sbd.modules.test.dao.CityDao;
import com.thornBird.sbd.modules.test.entity.City;
import com.thornBird.sbd.modules.test.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityDao cityDao;

	@Override
	public List<City> getCitiesByCountryId(int countryId) {
		List<City> cities = cityDao.getCitiesByCountryId2(countryId);
		return cities;
	}

	@Override
	public PageInfo<City> getCitiesByPage(int currentPage, int pageSize, int countryId) {
		PageHelper.startPage(currentPage, pageSize);
		List<City> cities = cityDao.getCitiesByCountryId(countryId);
//		cities.stream().map(item -> {
//			item.setCityName(item.getCityName() + "--");
//			return item;
//		}).collect(Collectors.toList());
		return new PageInfo<City>(cities);
	}

	@Override
	public City getCityByName(String cityName, String localCityName) {
		List<City> cities = Optional.ofNullable(cityDao.getCityByName2(cityName, localCityName))
				.orElse(Collections.emptyList());
		return cities.isEmpty() ? null : cities.get(0);
	}

	@Override
	public Result<City> insertCity(City city) {
		Result<City> result = new Result<>(ResultStatus.SUCCESS.status, "Insert success.");
		
		try {
			cityDao.insertCity(city);
			result.setObject(city);
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

	@Override
	@Transactional
	public Result<City> updateCity(City city) {
		Result<City> result = new Result<>(ResultStatus.SUCCESS.status, "Update success.");
		
		cityDao.updateCity(city);
		result.setObject(city);
//		int a = 1 / 0;
		
		return result;
	}

	@Override
	public Result<Object> deleteCity(int cityId) {
		Result<Object> result = new Result<>(ResultStatus.SUCCESS.status, "Delete success.");
		
		try {
			cityDao.deleteCity(cityId);
		} catch (Exception e) {
			result.setStatus(ResultStatus.FAILED.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

}
