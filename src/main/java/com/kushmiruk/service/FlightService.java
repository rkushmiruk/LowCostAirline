package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.FlightDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.Flight;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.RegexPattern;

import java.util.List;

/**
 * Service for interact with DAO layer interface FlightDao
 */
public class FlightService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static FlightDao flightDao = daoFactory.createFlightDao();

    private FlightService() {
    }

    private static class FlightServiceHolder {
        private static final FlightService instance = new FlightService();
    }

    public static FlightService getInstance() {
        return FlightServiceHolder.instance;
    }

    private static void checkCityInput(String cityName) {
        if (!cityName.matches(RegexPattern.CITY_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.CITY_PATTERN_ERROR));
        }
    }

    private static void checkDate(String date) {
        if (!date.matches(RegexPattern.DATE_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.DATE_PATTERN_ERROR));
        }
    }

    /**
     * Retrieves all flights with this params from database
     *
     * @param cityFromName departure city
     * @param cityToName   destination city
     * @param date         departure date
     * @return
     * @throws DaoException
     */
    public List<Flight> searchFlights(String cityFromName, String cityToName, String date) throws DaoException {
        List<Flight> result = flightDao.findFlights(cityFromName, cityToName, date);
        checkCityInput(cityFromName);
        checkCityInput(cityToName);
        checkDate(date);
        if (result.isEmpty()) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.FLIGHT_NOT_FOUND_ERROR));
        }
        return result;
    }
}
