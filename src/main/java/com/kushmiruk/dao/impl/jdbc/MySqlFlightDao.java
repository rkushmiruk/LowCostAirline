package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.FlightDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.location.Airport;
import com.kushmiruk.model.entity.order.Flight;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlFlightDao extends EntityDao<Flight> implements FlightDao {
    private static final String TABLE_NAME = "flight";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_DEPARTURE_AIRPORT = "departure_airport_id";
    private static final String PARAMETER_DESTINATION_AIRPORT = "destination_airport_id";
    private static final String PARAMETER_DEPARTURE_DATE = "departure_datetime";
    private static final String PARAMETER_DESTINATION_DATE = "destination_datetime";
    private static final String PARAMETER_FLIGHT_TIME = "flight_time";
    private static final String PARAMETER_TOTAL_SEATS_NUMBER = "total_seats_number";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 6;
    private static final Integer DEPARTURE_AIRPORT_INDEX = 1;
    private static final Integer DESTINATION_AIRPORT_INDEX = 2;
    private static final Integer DEPARTURE_DATE_INDEX = 3;
    private static final Integer DESTINATION_DATE_INDEX = 4;
    private static final Integer FLIGHT_TIME_INDEX = 5;
    private static final Integer TOTAL_SEATS_INDEX = 6;
    private static final Integer ID_INDEX = 7;

    private MySqlFlightDao() {
        super(TABLE_NAME);
    }

    private static class MySqlFlightDaoHolder {
        private static final MySqlFlightDao instance = new MySqlFlightDao();
    }

    public static MySqlFlightDao getInstance() {
        return MySqlFlightDaoHolder.instance;
    }

    @Override
    protected Optional<Flight> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        Airport departureAirport = null;
        Airport destinationAirport = null;
        if (MySqlAirportDao.getInstance().findById(resultSet.getLong(PARAMETER_DEPARTURE_AIRPORT)).isPresent() &&
                MySqlAirportDao.getInstance().findById(resultSet.getLong(PARAMETER_DESTINATION_AIRPORT)).isPresent()) {
            departureAirport = MySqlAirportDao.getInstance().findById(resultSet.getLong(PARAMETER_DEPARTURE_AIRPORT)).get();
            destinationAirport = MySqlAirportDao.getInstance().findById(resultSet.getLong(PARAMETER_DESTINATION_AIRPORT)).get();
        }
        Date departureDateTime = resultSet.getDate(PARAMETER_DEPARTURE_DATE);
        Integer flightTime = resultSet.getInt(PARAMETER_FLIGHT_TIME);
        Integer totalSeatsNumber = resultSet.getInt(PARAMETER_TOTAL_SEATS_NUMBER);

        return Optional.of(new Flight.Builder()
                .id(id)
                .departureAirport(departureAirport)
                .destinationAirport(destinationAirport)
                .departureDateTime(departureDateTime)
                .destinationDateTime()
                .flightTime(flightTime)
                .totalSeatsNumber(totalSeatsNumber)
                .build());
    }

    @Override
    protected void setEntityToParameters(Flight entity, PreparedStatement statement) throws SQLException {
        statement.setLong(DEPARTURE_AIRPORT_INDEX, entity.getDepartureAirport().getId());
        statement.setLong(DESTINATION_AIRPORT_INDEX, entity.getDestinationAirport().getId());
        statement.setDate(DEPARTURE_DATE_INDEX, entity.getDepartureDateTime());
        statement.setDate(DESTINATION_DATE_INDEX, entity.getDestinationDateTime());
        statement.setInt(FLIGHT_TIME_INDEX, entity.getFlightTime());
        statement.setInt(TOTAL_SEATS_INDEX, entity.getTotalSeatsNumber());
        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(Flight entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_DEPARTURE_AIRPORT;
        result[1] = PARAMETER_DESTINATION_AIRPORT;
        result[2] = PARAMETER_DEPARTURE_DATE;
        result[3] = PARAMETER_DESTINATION_DATE;
        result[4] = PARAMETER_FLIGHT_TIME;
        result[5] = PARAMETER_TOTAL_SEATS_NUMBER;
        return result;
    }
}
