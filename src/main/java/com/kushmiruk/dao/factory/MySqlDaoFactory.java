package com.kushmiruk.dao.factory;

import com.kushmiruk.dao.daointerface.*;
import com.kushmiruk.dao.impl.jdbc.MySqlAirportDao;

/**
 * MySql implementation for DaoFactory
 */
public class MySqlDaoFactory extends DaoFactory {
    private static final AirportDao airportDao = MySqlAirportDao.getInstance();

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
        return null;
    }

    @Override
    public CityDao createCityDao() {
        return null;
    }

    @Override
    public CountryDao createCountryDao() {
        return null;
    }

    @Override
    public ExtraPriceDao createExtraPriceDao() {
        return null;
    }

    @Override
    public FlightDao createFlightDao() {
        return null;
    }

    @Override
    public TicketDao createTicketDao() {
        return null;
    }

    @Override
    public TicketOrderDao createTicketOrderDao() {
        return null;
    }

    @Override
    public UserDao createUserDao() {
        return null;
    }
}
