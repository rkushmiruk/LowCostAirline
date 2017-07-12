package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.PaymentMethod;
import com.kushmiruk.model.entity.order.TicketOrder;
import com.kushmiruk.model.entity.user.User;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.QueryMessage;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlTicketOrderDao extends EntityDao<TicketOrder> implements TicketOrderDao {
    private static final Logger LOGGER = Logger.getLogger(MySqlTicketOrderDao.class);
    private static final String TABLE_NAME = "ticket_order";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_PAYMENT_METHOD = "payment_method";
    private static final String PARAMETER_DATETIME = "datetime";
    private static final String PARAMETER_USER = "user_id";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 2;
    private static final Integer PAYMENT_METHOD_INDEX = 1;
    private static final Integer USER_INDEX = 2;
    private static final Integer ID_INDEX = 3;

    private MySqlTicketOrderDao(Connection connection) {
        super(TABLE_NAME, connection);
    }

    private static class MySqlTicketOrderDaoHolder {

        private static MySqlTicketOrderDao instance(Connection connection) {
            return new MySqlTicketOrderDao(connection);
        }
    }

    public static MySqlTicketOrderDao getInstance(Connection connection) {
        return MySqlTicketOrderDaoHolder.instance(connection);
    }

    @Override
    public List<TicketOrder> findTicketOrdersByLogin(String login) {
        List<TicketOrder> result = new ArrayList<>();
        String query = QueryMessage.FIND_TICKET_ORDERS;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
            LOGGER.info(statement.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    if (getEntityFromResultSet(resultSet).isPresent()) {
                        result.add(getEntityFromResultSet(resultSet).get());
                    }
                    LOGGER.info(LoggerMessage.ITEMS + tableName + LoggerMessage.FOUND_IN_TABLE);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(LoggerMessage.DB_ERROR_SEARCH + tableName + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
        }
        return result;
    }

    @Override
    protected Optional<TicketOrder> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        PaymentMethod paymentMethod = PaymentMethod.valueOf(resultSet.getString(PARAMETER_PAYMENT_METHOD).toUpperCase());
        Optional<User> optionalUser = MySqlUserDao.getInstance(connection).findById(resultSet.getLong(PARAMETER_USER));
        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        Date date = resultSet.getDate(PARAMETER_DATETIME);

        return Optional.of(new TicketOrder(id, paymentMethod, user, date));
    }

    @Override
    protected void setEntityToParameters(TicketOrder entity, PreparedStatement statement) throws SQLException {
        statement.setString(PAYMENT_METHOD_INDEX, entity.getPaymentMethod().toString());
        if (entity.getUser() == null) {
            statement.setObject(USER_INDEX, null);
        } else {
            statement.setLong(USER_INDEX, entity.getUser().getId());
        }
        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(TicketOrder entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_PAYMENT_METHOD;
        result[1] = PARAMETER_USER;
        return result;
    }
}
