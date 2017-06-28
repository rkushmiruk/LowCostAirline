package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.FlightDao;
import com.kushmiruk.model.entity.order.Flight;

public class FlightService extends GenericService<Flight> {
    private static FlightDao flightDao = daoFactory.createFlightDao();

    private FlightService() {
        super(flightDao);
    }

    private static class FlightServiceHolder {
        private static final FlightService instance = new FlightService();
    }

    public static FlightService getInstance() {
        return FlightServiceHolder.instance;
    }
}
