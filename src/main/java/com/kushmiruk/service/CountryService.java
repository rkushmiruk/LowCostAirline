package com.kushmiruk.service;

/**
 * Service for interact with DAO layer interface CountryDao
 */
public class CountryService {

    private CountryService() {
    }

    private static class CountryServiceHolder {
        private static final CountryService instance = new CountryService();
    }

    public static CountryService getInstance() {
        return CountryServiceHolder.instance;
    }
}
