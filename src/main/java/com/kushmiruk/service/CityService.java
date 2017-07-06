package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.CityDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.model.entity.location.City;

/**
 * Service for interact with DAO layer interface CityDao
 */
public class CityService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static CityDao cityDao = daoFactory.createCityDao();

    private CityService() {
    }

    private static class CityServiceHolder {
        private static final CityService instance = new CityService();
    }

    public static CityService getInstance() {
        return CityServiceHolder.instance;
    }
}
