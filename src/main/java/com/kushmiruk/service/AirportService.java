package com.kushmiruk.service;

/**
 * Service for interact with DAO layer interface AirportDao
 */
public class AirportService {

    private AirportService() {
    }

    private static class AirportServiceHolder {
        private static final AirportService instance = new AirportService();
    }

    public static AirportService getInstance() {
        return AirportServiceHolder.instance;
    }
}
