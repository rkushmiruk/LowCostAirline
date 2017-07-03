package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.AirportDao;
import com.kushmiruk.model.entity.location.Airport;

/**
 *  Service for interact with DAO layer interface AirportDao
 */
public class AirportService extends GenericService<Airport> {
    private static AirportDao airportDao = daoFactory.createAirportDao();

    private AirportService() {
        super(airportDao);
    }

    private static class AirportServiceHolder {
        private static final AirportService instance = new AirportService();
    }

    public static AirportService getInstance() {
        return AirportServiceHolder.instance;
    }
}
