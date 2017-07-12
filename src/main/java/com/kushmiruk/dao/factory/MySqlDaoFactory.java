package com.kushmiruk.dao.factory;

import com.kushmiruk.dao.daointerface.*;
import com.kushmiruk.dao.impl.jdbc.*;

import java.sql.Connection;

/**
 * MySql implementation for DaoFactory
 */
public class MySqlDaoFactory extends DaoFactory {
    private Connection connection;

    private MySqlDaoFactory(Connection connection) {
        this.connection = connection;
    }

    private static class MySqlDaoFactoryHolder {
        private static MySqlDaoFactory instance(Connection connection) {
            return new MySqlDaoFactory(connection);
        }
    }

    public static MySqlDaoFactory getInstance(Connection connection) {
        return MySqlDaoFactoryHolder.instance(connection);
    }

    @Override
    public AirportDao createAirportDao() {
        return MySqlAirportDao.getInstance(connection);
    }

    @Override
    public CityDao createCityDao() {
        return MySqlCityDao.getInstance(connection);
    }

    @Override
    public CountryDao createCountryDao() {
        return MySqlCountryDao.getInstance(connection);
    }

    @Override
    public ExtraPriceDao createExtraPriceDao() {
        return MySqlExtraPriceDao.getInstance(connection);
    }

    @Override
    public FlightDao createFlightDao() {
        return MySqlFlightDao.getInstance(connection);
    }

    @Override
    public TicketDao createTicketDao() {
        return MySqlTicketDao.getInstance(connection);
    }

    @Override
    public TicketOrderDao createTicketOrderDao() {
        return MySqlTicketOrderDao.getInstance(connection);
    }

    @Override
    public UserDao createUserDao() {
        return MySqlUserDao.getInstance(connection);
    }

    @Override
    public UserAuthenticationDao createUserAuthenticationDao() {
        return MySqlUserAuthenticationDao.getInstance(connection);
    }
}
