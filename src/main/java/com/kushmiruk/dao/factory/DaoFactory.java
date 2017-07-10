package com.kushmiruk.dao.factory;

import com.kushmiruk.dao.daointerface.*;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * Abstract factory for application DAO
 */
public abstract class DaoFactory {
    private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

    public abstract AirportDao createAirportDao();

    public abstract BaggageDao createBaggageDao();

    public abstract CityDao createCityDao();

    public abstract CountryDao createCountryDao();

    public abstract ExtraPriceDao createExtraPriceDao();

    public abstract FlightDao createFlightDao();

    public abstract TicketDao createTicketDao();

    public abstract TicketOrderDao createTicketOrderDao();

    public abstract UserDao createUserDao();

    public abstract UserAuthenticationDao createUserAuthenticationDao();

    public static DaoFactory getDaoFactory(Connection connection) {
        LOGGER.info(connection);
        return MySqlDaoFactory.getInstance(connection);
    }
}
