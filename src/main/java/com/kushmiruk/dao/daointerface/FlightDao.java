package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.order.Flight;

import java.util.List;

/**
 * DAO interface for CRUD operations with entity Flight
 */
public interface FlightDao extends GenericDao<Flight, Long> {

    /**
     * Retrieves all flights from database from first city to second in date.
     *
     * @param fromCityName departure city
     * @param toCityName   destination city
     * @param date         date of flight
     * @return List of flights
     */
    List<Flight> findFlights(String fromCityName, String toCityName, String date);
}
