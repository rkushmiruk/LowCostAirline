package com.kushmiruk.dao.factory;

import com.kushmiruk.dao.daointerface.*;
import com.kushmiruk.dao.impl.jdbc.*;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * MySql implementation for DaoFactory
 */
public class MySqlDaoFactory extends DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(MySqlDaoFactory.class);
    private static Connection connection;
    private AirportDao airportDao = MySqlAirportDao.getInstance(connection);
    private CountryDao countryDao = MySqlCountryDao.getInstance(connection);
    private CityDao cityDao = MySqlCityDao.getInstance(connection);
    private UserDao userDao = MySqlUserDao.getInstance(connection);
    private UserAuthenticationDao userAuthenticationDao = MySqlUserAuthenticationDao.getInstance(connection);
    private BaggageDao baggageDao = MySqlBaggageDao.getInstance(connection);
    private FlightDao flightDao = MySqlFlightDao.getInstance(connection);
    private TicketDao ticketDao = MySqlTicketDao.getInstance(connection);
    private ExtraPriceDao extraPriceDao = MySqlExtraPriceDao.getInstance(connection);
    private TicketOrderDao ticketOrderDao = MySqlTicketOrderDao.getInstance(connection);

    private MySqlDaoFactory(Connection connection) {
        LOGGER.info(connection);

    }

    private static class MySqlDaoFactoryHolder {
        private static final MySqlDaoFactory instance(Connection connection) {
            return new MySqlDaoFactory(connection);
        }
    }

    public static MySqlDaoFactory getInstance(Connection connection) {
        MySqlDaoFactory.connection = connection;
        return MySqlDaoFactoryHolder.instance(connection);
    }

    @Override
    public AirportDao createAirportDao() {
        return airportDao;
    }

    @Override
    public BaggageDao createBaggageDao() {
        return baggageDao;
    }

    @Override
    public CityDao createCityDao() {
        return cityDao;
    }

    @Override
    public CountryDao createCountryDao() {
        return countryDao;
    }

    @Override
    public ExtraPriceDao createExtraPriceDao() {
        return extraPriceDao;
    }

    @Override
    public FlightDao createFlightDao() {
        return flightDao;
    }

    @Override
    public TicketDao createTicketDao() {
        return ticketDao;
    }

    @Override
    public TicketOrderDao createTicketOrderDao() {
        return ticketOrderDao;
    }

    @Override
    public UserDao createUserDao() {
        return userDao;
    }

    @Override
    public UserAuthenticationDao createUserAuthenticationDao() {
        return userAuthenticationDao;
    }
}
