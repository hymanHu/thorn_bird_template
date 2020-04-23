package com.thornBird.sbd.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thornBird.sbd.modules.test.dao.CountryDao;
import com.thornBird.sbd.modules.test.entity.Country;
import com.thornBird.sbd.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	
	@Autowired
	private CountryDao countryDao;

	@Override
	public Country getCountryById(int countryId) {
		return countryDao.getCountryById(countryId);
	}

	@Override
	public Country getCountryByName(String countryName) {
		return countryDao.getCountryByName(countryName);
	}

}
