package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.FlightDao;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.Flight;

import java.util.List;

/**
 *  Service for interact with DAO layer interface FlightDao
 */
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

    public List<Flight> searchFlights(String cityFromName, String cityToName, String date) throws DaoException {
        List<Flight> result = flightDao.findFlights(cityFromName, cityToName, date);
        if (result.isEmpty()) {
            throw new DaoException("No flights found");
        }
        return result;
    }
}
