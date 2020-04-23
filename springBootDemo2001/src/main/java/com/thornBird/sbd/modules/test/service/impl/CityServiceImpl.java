package com.thornBird.sbd.modules.test.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thornBird.sbd.modules.common.vo.Result;
import com.thornBird.sbd.modules.common.vo.Result.ResultEnum;
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
		return new PageInfo<City>(cities);
	}

	@Override
	public Result<City> insertCity(City city) {
		Result<City> result = new Result<>(ResultEnum.SUCCESS.status, "Insert success.");
		
		try {
			cityDao.insertCity(city);
			result.setObject(city);
		} catch (Exception e) {
			result.setStatus(ResultEnum.FAILD.status);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}

}
