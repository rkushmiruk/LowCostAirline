package com.kushmiruk.dao.factory;

import com.kushmiruk.dao.daointerface.*;
import com.kushmiruk.dao.impl.jdbc.*;
import com.kushmiruk.model.entity.user.UserAuthentication;

/**
 * MySql implementation for DaoFactory
 */
public class MySqlDaoFactory extends DaoFactory {
    private static final AirportDao airportDao = MySqlAirportDao.getInstance();
    private static final CountryDao countryDao = MySqlCountryDao.getInstance();
    private static final CityDao cityDao = MySqlCityDao.getInstance();
    private static final UserDao userDao = MySqlUserDao.getInstance();
    private static final UserAuthenticationDao userAuthenticationDao = MySqlUserAuthenticationDao.getInstance();
    private static final BaggageDao baggageDao = MySqlBaggageDao.getInstance();
    private static final FlightDao flightDao = MySqlFlightDao.getInstance();
    private static final TicketDao ticketDao = MySqlTicketDao.getInstance();
    private static final ExtraPriceDao extraPriceDao = MySqlExtraPriceDao.getInstance();
    private static final TicketOrderDao ticketOrderDao = MySqlTicketOrderDao.getInstance();

    private MySqlDaoFactory() {
    }

    private static class MySqlDaoFactoryHolder {
        private static final MySqlDaoFactory instance = new MySqlDaoFactory();
    }

    public static MySqlDaoFactory getInstance() {
        return MySqlDaoFactoryHolder.instance;
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
