package com.kushmiruk.service;

/**
 * Service for interact with DAO layer interface CityDao
 */
public class CityService {
    private CityService() {
    }

    private static class CityServiceHolder {
        private static final CityService instance = new CityService();
    }

    public static CityService getInstance() {
        return CityServiceHolder.instance;
    }
}
