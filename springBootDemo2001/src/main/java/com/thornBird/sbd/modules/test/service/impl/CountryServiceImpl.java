package com.thornBird.sbd.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thornBird.sbd.modules.test.dao.CountryDao;
import com.thornBird.sbd.modules.test.entity.Country;
import com.thornBird.sbd.modules.test.service.CountryService;
import com.thornBird.sbd.util.RedisUtils;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDao countryDao;
	@Autowired
	private RedisUtils redisUtils;

	@Override
	public Country getCountryById(int countryId) {
		return countryDao.getCountryById(countryId);
	}

	@Override
	public Country getCountryByName(String countryName) {
		return countryDao.getCountryByName(countryName);
	}

	@Override
	public Object migrateCountryByCountryId(int countryId) {
		Country country = countryDao.getCountryById(countryId);
		
		String key = String.format("country%d", country.getCountryId());
		redisUtils.set(key, country);
		
		return redisUtils.get(key);
	}

}
