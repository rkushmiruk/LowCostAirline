package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.BaggageDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.Baggage;
import com.kushmiruk.model.entity.order.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlBaggageDao extends EntityDao<Baggage> implements BaggageDao {
    private static final String TABLE_NAME = "baggage";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_WEIGHT = "weight";
    private static final String PARAMETER_AMOUNT = "amount";
    private static final String PARAMETER_PRICE = "price";
    private static final String PARAMETER_TICKET_ID = "ticket_id";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 4;
    private static final Integer WEIGHT_INDEX = 1;
    private static final Integer AMOUNT_INDEX = 2;
    private static final Integer PRICE_INDEX = 3;
    private static final Integer TICKET_INDEX = 4;
    private static final Integer ID_INDEX = 5;

    private MySqlBaggageDao() {
        super(TABLE_NAME);
    }

    private static class MySqlBaggageDaoHolder {
        private static final MySqlBaggageDao instance = new MySqlBaggageDao();
    }

    public static MySqlBaggageDao getInstance() {
        return MySqlBaggageDaoHolder.instance;
    }

    @Override
    protected Optional<Baggage> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        Integer weight = resultSet.getInt(PARAMETER_WEIGHT);
        Integer amount = resultSet.getInt(PARAMETER_AMOUNT);
        Integer price = resultSet.getInt(PARAMETER_PRICE);
        Ticket ticket = null;

        return Optional.of(new Baggage(id, weight, amount, price, ticket));
    }

    @Override
    protected void setEntityToParameters(Baggage entity, PreparedStatement statement) throws SQLException {
        statement.setInt(WEIGHT_INDEX, entity.getWeight());
        statement.setInt(AMOUNT_INDEX, entity.getAmount());
        statement.setInt(PRICE_INDEX, entity.getPrice());
        statement.setLong(TICKET_INDEX, entity.getTicket().getId());


        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(Baggage entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_WEIGHT;
        result[1] = PARAMETER_AMOUNT;
        result[2] = PARAMETER_PRICE;
        result[3] = PARAMETER_TICKET_ID;
        return result;

    }
}
