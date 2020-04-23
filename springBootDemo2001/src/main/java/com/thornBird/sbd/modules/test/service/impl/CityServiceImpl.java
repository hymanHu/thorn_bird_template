package com.thornBird.sbd.modules.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
