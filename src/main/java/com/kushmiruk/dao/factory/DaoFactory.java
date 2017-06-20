package com.kushmiruk.dao.factory;

import com.kushmiruk.dao.daointerface.*;

/**
 * Abstract factory for application DAO
 */
public abstract class DaoFactory {
    public abstract AirportDao createAirportDao();

    public abstract BaggageDao createBaggageDao();

    public abstract CityDao createCityDao();

    public abstract CountryDao createCountryDao();

    public abstract ExtraPriceDao createExtraPriceDao();

    public abstract FlightDao createFlightDao();

    public abstract TicketDao createTicketDao();

    public abstract TicketOrderDao createTicketOrderDao();

    public abstract UserDao createUserDao();

    public static DaoFactory getDaoFactory() {
        return MySqlDaoFactory.getInstance();
    }
}
