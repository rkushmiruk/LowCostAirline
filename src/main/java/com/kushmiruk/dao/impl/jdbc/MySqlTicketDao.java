package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.dao.impl.util.JoinType;
import com.kushmiruk.model.entity.order.*;
import com.kushmiruk.util.LoggerMessage;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlTicketDao extends EntityDao<Ticket> implements TicketDao {
    private static final Logger LOGGER = Logger.getLogger(MySqlTicketDao.class);
    private static final String TABLE_NAME = "ticket";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_FIRST_NAME = "passenger_first_name";
    private static final String PARAMETER_LAST_NAME = "passenger_last_name";
    private static final String PARAMETER_EMAIL = "passenger_email";
    private static final String PARAMETER_PRIORITY_REGISTRATION = "has_priority_registration";
    private static final String PARAMETER_BAGGAGE_ID = "baggage_id";
    private static final String PARAMETER_PRICE = "price";
    private static final String PARAMETER_SEAT_NUMBER = "seat_number";
    private static final String PARAMETER_TICKET_ORDER = "order_id";
    private static final String PARAMETER_FLIGHT = "flight_id";
    private static final String PARAMETER_EXTRA_PRICE = "extra_price_id";
    private static final String PARAMETER_STATUS_ID = "status_id";
    private static final String PARAMETER_STATUS = "STATUS";
    private static final String PARAMETER_BAGGAGE = "type";
    private static final String STATUS_TABLE = "ticket_status";
    private static final String BAGGAGE_TABLE = "baggage_type";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 11;
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
    private static final Integer TICKET_STATUS_INDEX = 11;
    private static final Integer ID_INDEX = 12;
    private static final Integer STATUS_ID_INDEX = 1;

    private MySqlTicketDao(Connection connection) {
        super(TABLE_NAME, connection);
    }

    private static class MySqlTicketDaoHolder {
        private static MySqlTicketDao instance(Connection connection) {
            return new MySqlTicketDao(connection);
        }
    }

    public static MySqlTicketDao getInstance(Connection connection) {
        return MySqlTicketDaoHolder.instance(connection);
    }

    @Override
    public List<Integer> findAllSellTicketsSeatNumbers(Long flightId) {
        List<Integer> result = new ArrayList<>();
        String query = selectQueryBuilder
                .table(tableName)
                .getAll()
                .from()
                .condition(PARAMETER_FLIGHT)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, flightId);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (getEntityFromResultSet(resultSet).isPresent()) {
                        result.add(getEntityFromResultSet(resultSet).get().getSeatNumber());
                    }
                }
            }
            LOGGER.info(LoggerMessage.ITEMS + tableName + LoggerMessage.FOUND_IN_TABLE);
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return result;
    }

    @Override
    public Optional<String> findTicketStatus(Long id) {
        String query = selectQueryBuilder
                .table(TABLE_NAME)
                .field(PARAMETER_STATUS)
                .from()
                .join(JoinType.INNER, STATUS_TABLE, PARAMETER_STATUS_ID, PARAMETER_ID)
                .condition(PARAMETER_ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(STATUS_ID_INDEX, id);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info(LoggerMessage.ITEM + STATUS_TABLE + LoggerMessage.WITH_ID + id);
                    return Optional.of(resultSet.getString(PARAMETER_STATUS));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + STATUS_TABLE + LoggerMessage.ITEM_WITH_ID + id +
                    LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }

        return Optional.empty();
    }

    public Optional<String> findTicketBaggage(Long id) {
        String query = selectQueryBuilder
                .table(TABLE_NAME)
                .field(PARAMETER_BAGGAGE)
                .from()
                .join(JoinType.INNER, BAGGAGE_TABLE, PARAMETER_BAGGAGE_ID, PARAMETER_ID)
                .condition(PARAMETER_ID)
                .build();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(STATUS_ID_INDEX, id);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info(LoggerMessage.ITEM + STATUS_TABLE + LoggerMessage.WITH_ID + id);
                    return Optional.of(resultSet.getString(PARAMETER_STATUS));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + STATUS_TABLE + LoggerMessage.ITEM_WITH_ID + id +
                    LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }

        return Optional.empty();
    }


    @Override
    protected Optional<Ticket> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String firstName = resultSet.getString(PARAMETER_FIRST_NAME);
        String lastName = resultSet.getString(PARAMETER_LAST_NAME);
        String email = resultSet.getString(PARAMETER_EMAIL);
        Boolean hasPriorityRegistration = resultSet.getBoolean(PARAMETER_PRIORITY_REGISTRATION);
        Baggage baggage = null;
        if (findTicketBaggage(resultSet.getLong(PARAMETER_BAGGAGE_ID)).isPresent()) {
            baggage = Baggage.valueOf(findTicketBaggage(resultSet.getLong(PARAMETER_BAGGAGE_ID)).get());
        }
        Long price = resultSet.getLong(PARAMETER_PRICE);
        Integer seatNUmber = resultSet.getInt(PARAMETER_SEAT_NUMBER);
        Optional<TicketOrder> optionalTickerOrder = MySqlTicketOrderDao.getInstance(connection).findById(resultSet.getLong(PARAMETER_TICKET_ORDER));
        TicketOrder ticketOrder = null;
        if (optionalTickerOrder.isPresent()) {
            ticketOrder = optionalTickerOrder.get();
        }
        Optional<Flight> optionalFlight = MySqlFlightDao.getInstance(connection).findById(resultSet.getLong(PARAMETER_FLIGHT));
        Flight flight = null;
        if (optionalFlight.isPresent()) {
            flight = optionalFlight.get();
        }
        Optional<ExtraPrice> optionalExtraPrice = MySqlExtraPriceDao.getInstance(connection).findById(resultSet.getLong(PARAMETER_EXTRA_PRICE));
        ExtraPrice extraPrice = null;
        if (optionalExtraPrice.isPresent()) {
            extraPrice = optionalExtraPrice.get();
        }
        TicketStatus ticketStatus = null;
        if (findTicketStatus(resultSet.getLong(PARAMETER_STATUS_ID)).isPresent()) {
            ticketStatus = TicketStatus.valueOf(findTicketStatus(resultSet.getLong(PARAMETER_STATUS_ID)).get());
        }

        return Optional.of(new Ticket.Builder()
                .id(id)
                .passengerFirstName(firstName)
                .passengerLastName(lastName)
                .email(email)
                .hasPriorityRegistration(hasPriorityRegistration)
                .baggage(baggage)
                .price(price)
                .seatNumber(seatNUmber)
                .ticketOrder(ticketOrder)
                .flight(flight)
                .extraPrice(extraPrice)
                .ticketStatus(ticketStatus)
                .build());
    }

    @Override
    protected void setEntityToParameters(Ticket entity, PreparedStatement statement) throws SQLException {
        statement.setString(FIRST_NAME_INDEX, entity.getPassangerFirstName());
        statement.setString(LAST_NAME_INDEX, entity.getPassngerLastName());
        statement.setString(EMAIL_INDEX, entity.getEmail());
        statement.setBoolean(PRIORITY_REGISTRATION_INDEX, entity.getHasPriorityRegistration());
        statement.setLong(BAGGAGE_INDEX, entity.getBaggage().getId());
        statement.setLong(PRICE_INDEX, entity.getPrice());
        statement.setInt(SEAT_NUMBER_INDEX, entity.getSeatNumber());
        statement.setLong(TICKET_ORDER_INDEX, entity.getTicketOrder().getId());
        statement.setLong(FLIGHT_INDEX, entity.getFlight().getId());
        statement.setLong(EXTRA_PRICE_INDEX, entity.getExtraPrice().getId());
        statement.setLong(TICKET_STATUS_INDEX, entity.getTicketStatus().getId());
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
        result[4] = PARAMETER_BAGGAGE_ID;
        result[5] = PARAMETER_PRICE;
        result[6] = PARAMETER_SEAT_NUMBER;
        result[7] = PARAMETER_TICKET_ORDER;
        result[8] = PARAMETER_FLIGHT;
        result[9] = PARAMETER_EXTRA_PRICE;
        result[10] = PARAMETER_STATUS_ID;
        return result;
    }
}
