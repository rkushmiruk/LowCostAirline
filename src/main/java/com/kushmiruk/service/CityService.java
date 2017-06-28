package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.CityDao;
import com.kushmiruk.model.entity.location.City;

public class CityService extends GenericService<City> {
    private static CityDao cityDao = daoFactory.createCityDao();

    private CityService() {
        super(cityDao);
    }

    private static class CityServiceHolder {
        private static final CityService instance = new CityService();
    }

    public static CityService getInstance() {
        return CityServiceHolder.instance;
    }
}
