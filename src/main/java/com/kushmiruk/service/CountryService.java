package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.CountryDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.model.entity.location.Country;

/**
 * Service for interact with DAO layer interface CountryDao
 */
public class CountryService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static CountryDao countryDao = daoFactory.createCountryDao();

    private CountryService() {
    }

    private static class CountryServiceHolder {
        private static final CountryService instance = new CountryService();
    }

    public static CountryService getInstance() {
        return CountryServiceHolder.instance;
    }
}
