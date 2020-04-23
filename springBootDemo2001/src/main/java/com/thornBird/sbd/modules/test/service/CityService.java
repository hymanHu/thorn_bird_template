package com.thornBird.sbd.modules.test.service;

import java.util.List;

import com.thornBird.sbd.modules.test.entity.City;

public interface CityService {

	List<City> getCitiesByCountryId(int countryId);
}
