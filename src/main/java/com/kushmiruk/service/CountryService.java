package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.CountryDao;
import com.kushmiruk.model.entity.location.Country;

public class CountryService extends GenericService<Country> {
    private static CountryDao countryDao = daoFactory.createCountryDao();

    private CountryService() {
        super(countryDao);
    }

    private static class CountryServiceHolder {
        private static final CountryService instance = new CountryService();
    }

    public static CountryService getInstance() {
        return CountryServiceHolder.instance;
    }
}
