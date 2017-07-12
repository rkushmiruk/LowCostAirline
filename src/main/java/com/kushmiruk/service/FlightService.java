package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.FlightDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.Flight;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.RegexPattern;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 * Service for interact with DAO layer interface FlightDao
 */
public class FlightService {
    private FlightService() {
    }

    private static class FlightServiceHolder {

        private static final FlightService instance = new FlightService();
    }

    public static FlightService getInstance() {
        return FlightServiceHolder.instance;
    }

    /**
     * Check cityNam with regex if operation is not successful throw new
     * DaoException
     *
     * @param cityName
     */
    private static void checkCityInput(String cityName) {
        if (!cityName.matches(RegexPattern.CITY_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.CITY_PATTERN_ERROR));
        }
    }

    /**
     * Check date with regex if operation is not successful throw new
     * DaoException
     *
     * @param date
     */
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
     * @return list of flights
     * @throws DaoException if some error occurs while searching
     */
    public List<Flight> searchFlights(String cityFromName, String cityToName, String date) throws DaoException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        List<Flight> result;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            FlightDao flightDao = daoFactory.createFlightDao();
            checkCityInput(cityFromName);
            checkCityInput(cityToName);
            checkDate(date);
            result = flightDao.findFlights(cityFromName, cityToName, date);
            if (result.isEmpty()) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.FLIGHT_NOT_FOUND_ERROR));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return result;
    }

    /**
     * Retrieves flight by id from database
     *
     * @param id Identifier of flight
     * @return flight
     * @throws DaoException if some error occurs while performing some logic
     */
    public Optional<Flight> findFlight(Long id) throws DaoException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        Optional<Flight> value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            FlightDao flightDao = daoFactory.createFlightDao();
            value = flightDao.findById(id);
            if (!value.isPresent()) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.FLIGHT_NOT_FOUND_ERROR));
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

}
