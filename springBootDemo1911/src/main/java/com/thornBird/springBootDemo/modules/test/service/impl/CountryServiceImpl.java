package com.thornBird.springBootDemo.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.thornBird.springBootDemo.modules.test.dao.CountryDao;
import com.thornBird.springBootDemo.modules.test.entity.City;
import com.thornBird.springBootDemo.modules.test.entity.Country;
import com.thornBird.springBootDemo.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDao countryDao;

	@Override
	public Country getCountryById(int countryId) {
		return countryDao.getCountryById(countryId);
	}

	@Override
	public List<City> getCitiesByCountryId(int countryId) {
//		return countryDao.getCitiesByCountryId(countryId);
		return Optional.ofNullable(countryDao.getCitiesByCountryId(countryId))
				.orElse(Collections.emptyList());
	}

	@Override
	public Country getCountryByName(String countryName) {
		return countryDao.getCountryByName(countryName);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<City> getCitiesByPage(int countryId, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<City> cities = Optional.ofNullable(countryDao.getCitiesByCountryId(countryId))
				.orElse(Collections.emptyList());
		return new PageInfo(cities);
	}

	@Override
	public City insertCity(City city) {
		countryDao.insertCity(city);
		return city;
	}

	@Transactional(propagation=Propagation.REQUIRED, noRollbackFor=ArithmeticException.class)
	@Override
	public City updateCity(City city) {
		countryDao.updateCity(city);
//		int i = 1 / 0;
		return city;
	}

	@Override
	public void deleteCity(int cityId) {
		countryDao.deleteCity(cityId);
	}
}
