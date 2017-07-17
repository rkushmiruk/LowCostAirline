package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.ExtraPriceDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.ExtraPrice;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlExtraPriceDao extends EntityDao<ExtraPrice> implements ExtraPriceDao {
    private static final String TABLE_NAME = "extra_price";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_PRIORITY_REGISTRATION_PRICE = "priority_registration_price";
    private static final String PARAMETER_PURCHASE_DATETIME = "days_before_flight";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 2;
    private static final Integer REGISTRATION_PRICE_INDEX = 1;
    private static final Integer PURCHASE_DATETIME_INDEX = 2;
    private static final Integer ID_INDEX = 3;

    private MySqlExtraPriceDao(Connection connection) {
        super(TABLE_NAME, connection);
    }

    private static class MySqlExtraPriceDaoHolder {
        private static MySqlExtraPriceDao instance(Connection connection) {
            return new MySqlExtraPriceDao(connection);
        }
    }

    public static MySqlExtraPriceDao getInstance(Connection connection) {
        return MySqlExtraPriceDaoHolder.instance(connection);
    }

    @Override
    protected Optional<ExtraPrice> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        Integer priorityRegistrationPrice = resultSet.getInt(PARAMETER_PRIORITY_REGISTRATION_PRICE);
        Integer purchaseDateTime = resultSet.getInt(PARAMETER_PURCHASE_DATETIME);
        return Optional.of(new ExtraPrice(id, priorityRegistrationPrice, purchaseDateTime));
    }

    @Override
    protected void setEntityToParameters(ExtraPrice entity, PreparedStatement statement) throws SQLException {
        statement.setInt(REGISTRATION_PRICE_INDEX, entity.getPriorityRegistrationPrice());
        statement.setInt(PURCHASE_DATETIME_INDEX, entity.getDaysBeforeFlight());
        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(ExtraPrice entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_PRIORITY_REGISTRATION_PRICE;
        result[1] = PARAMETER_PURCHASE_DATETIME;
        return result;
    }
}
