package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.ExtraPrice;
import com.kushmiruk.model.entity.order.Flight;
import com.kushmiruk.model.entity.order.Ticket;
import com.kushmiruk.model.entity.order.TicketOrder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlTicketDao extends EntityDao<Ticket> implements TicketDao {
    private static final String TABLE_NAME = "ticket";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_FIRST_NAME = "passenger_first_name";
    private static final String PARAMETER_LAST_NAME = "passenger_last_name";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_PRIORITY_REGISTRATION = "has_priority_registration";
    private static final String PARAMETER_BAGGAGE = "has_baggage";
    private static final String PARAMETER_PRICE = "price";
    private static final String PARAMETER_SEAT_NUMBER = "seat_number";
    private static final String PARAMETER_TICKET_ORDER = "ticket_id";
    private static final String PARAMETER_FLIGHT = "flight_id";
    private static final String PARAMETER_EXTRA_PRICE = "extra_price_id";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 10;
    private static final Integer FIRST_NAME_INDEX = 1;
    private static final Integer LAST_NAME_INDEX = 2;
    private static final Integer EMAIL_INDEX = 3;
    private static final Integer PRIORITY_REGISTRATION_INDEX = 4;
    private static final Integer BAGGAGE_INDEX = 5;
    private static final Integer PRICE_INDEX = 6;
    private static final Integer SEAT_NUMBER_INDEX = 7;
    private static final Integer TICKET_ORDER_INDEX = 8;
    private static final Integer FLIGHT_INDEX = 9;
    private static final Integer EXTRA_PRICE_INDEX = 10;
    private static final Integer ID_INDEX = 11;

    private MySqlTicketDao() {
        super(TABLE_NAME);
    }

    private static class MySqlTicketDaoHolder {
        private static final MySqlTicketDao instance = new MySqlTicketDao();
    }

    public static MySqlTicketDao getInstance() {
        return MySqlTicketDaoHolder.instance;
    }

    @Override
    protected Optional<Ticket> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String firstName = resultSet.getString(PARAMETER_FIRST_NAME);
        String lastName = resultSet.getString(PARAMETER_LAST_NAME);
        String email = resultSet.getString(PARAMETER_EMAIL);
        Boolean hasPriorityRegistration = resultSet.getBoolean(PARAMETER_PRIORITY_REGISTRATION);
        Boolean hasBaggage = resultSet.getBoolean(PARAMETER_BAGGAGE);
        Integer price = resultSet.getInt(PARAMETER_PRICE);
        Integer seatNUmber = resultSet.getInt(PARAMETER_SEAT_NUMBER);
        TicketOrder ticketOrder = null;
        if (MySqlTicketOrderDao.getInstance().findById(resultSet.getLong(PARAMETER_TICKET_ORDER)).isPresent()) {
            ticketOrder = MySqlTicketOrderDao.getInstance().findById(resultSet.getLong(PARAMETER_TICKET_ORDER)).get();
        }
        Flight flight = null;
        if (MySqlFlightDao.getInstance().findById(resultSet.getLong(PARAMETER_FLIGHT)).isPresent()) {
            flight = MySqlFlightDao.getInstance().findById(resultSet.getLong(PARAMETER_FLIGHT)).get();
        }
        ExtraPrice extraPrice = null;
        if (MySqlExtraPriceDao.getInstance().findById(resultSet.getLong(PARAMETER_EXTRA_PRICE)).isPresent()) {
            extraPrice = MySqlExtraPriceDao.getInstance().findById(resultSet.getLong(PARAMETER_EXTRA_PRICE)).get();
        }

        return Optional.of(new Ticket.Builder()
                .id(id)
                .passangerFirstName(firstName)
                .passangerLastName(lastName)
                .email(email)
                .hasPriorityRegistration(hasPriorityRegistration)
                .hasBaggage(hasBaggage)
                .price(price)
                .seatNumber(seatNUmber)
                .ticketOrder(ticketOrder)
                .flight(flight)
                .extraPrice(extraPrice)
                .build());
    }

    @Override
    protected void setEntityToParameters(Ticket entity, PreparedStatement statement) throws SQLException {
        statement.setString(FIRST_NAME_INDEX, entity.getPassangerFirstName());
        statement.setString(LAST_NAME_INDEX, entity.getPassngerLastName());
        statement.setString(EMAIL_INDEX, entity.getEmail());
        statement.setBoolean(PRIORITY_REGISTRATION_INDEX, entity.getHasPriorityRegistration());
        statement.setBoolean(BAGGAGE_INDEX, entity.getHasBaggage());
        statement.setInt(PRICE_INDEX, entity.getPrice());
        statement.setInt(SEAT_NUMBER_INDEX, entity.getSeatNumber());
        statement.setLong(TICKET_ORDER_INDEX, entity.getTicketOrder().getId());
        statement.setLong(FLIGHT_INDEX, entity.getFlight().getId());
        statement.setLong(EXTRA_PRICE_INDEX, entity.getExtraPrice().getId());
        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(Ticket entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_FIRST_NAME;
        result[1] = PARAMETER_LAST_NAME;
        result[2] = PARAMETER_EMAIL;
        result[3] = PARAMETER_PRIORITY_REGISTRATION;
        result[4] = PARAMETER_BAGGAGE;
        result[5] = PARAMETER_PRICE;
        result[6] = PARAMETER_SEAT_NUMBER;
        result[7] = PARAMETER_TICKET_ORDER;
        result[8] = PARAMETER_FLIGHT;
        result[9] = PARAMETER_EXTRA_PRICE;
        return result;
    }
}
