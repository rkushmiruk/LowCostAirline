package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.AirportDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.model.entity.location.Airport;

/**
 * Service for interact with DAO layer interface AirportDao
 */
public class AirportService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static final AirportDao airportDao = daoFactory.createAirportDao();

    private AirportService() {
    }

    private static class AirportServiceHolder {
        private static final AirportService instance = new AirportService();
    }

    public static AirportService getInstance() {
        return AirportServiceHolder.instance;
    }
}
