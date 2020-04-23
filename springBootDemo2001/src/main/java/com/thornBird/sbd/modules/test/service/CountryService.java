package com.thornBird.sbd.modules.test.service;

import com.thornBird.sbd.modules.test.entity.Country;

public interface CountryService {

	Country getCountryById(int countryId);
	
	Country getCountryByName(String countryName);
}
