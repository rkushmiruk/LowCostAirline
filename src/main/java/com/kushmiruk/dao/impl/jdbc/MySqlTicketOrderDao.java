package com.kushmiruk.dao.impl.jdbc;

import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.dao.impl.EntityDao;
import com.kushmiruk.model.entity.order.PaymentMethod;
import com.kushmiruk.model.entity.order.TicketOrder;
import com.kushmiruk.model.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * MySql implementation for CountryDao interface
 */
public class MySqlTicketOrderDao extends EntityDao<TicketOrder> implements TicketOrderDao {
    private static final String TABLE_NAME = "ticket_order";
    private static final String PARAMETER_ID = "id";
    private static final String PARAMETER_EMAIL = "email";
    private static final String PARAMETER_PAYMENT_METHOD = "payment_method";
    private static final String PARAMETER_USER = "user_id";
    private static final Integer PARAMETER_NUMBERS_WITHOUT_ID = 3;
    private static final Integer EMAIL_INDEX = 1;
    private static final Integer PAYMENT_METHOD_INDEX = 2;
    private static final Integer USER_INDEX = 3;
    private static final Integer ID_INDEX = 4;

    private MySqlTicketOrderDao() {
        super(TABLE_NAME);
    }

    private static class MySqlTicketOrderDaoHolder {
        private static MySqlTicketOrderDao instance = new MySqlTicketOrderDao();
    }

    public static MySqlTicketOrderDao getInstance() {
        return MySqlTicketOrderDaoHolder.instance;
    }

    @Override
    protected Optional<TicketOrder> getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(PARAMETER_ID);
        String email = resultSet.getString(PARAMETER_EMAIL);
        PaymentMethod paymentMethod = PaymentMethod.valueOf(resultSet.getString(PARAMETER_PAYMENT_METHOD).toUpperCase());
        User user = null;
        if (MySqlUserDao.getInstance().findById(resultSet.getLong(PARAMETER_USER)).isPresent()) {
            user = MySqlUserDao.getInstance().findById(resultSet.getLong(PARAMETER_USER)).get();
        }

        return Optional.of(new TicketOrder(id, email, paymentMethod, user));
    }

    @Override
    protected void setEntityToParameters(TicketOrder entity, PreparedStatement statement) throws SQLException {
        statement.setString(EMAIL_INDEX, entity.getEmail());
        statement.setString(PAYMENT_METHOD_INDEX, entity.getPaymentMethod().toString());
        statement.setLong(USER_INDEX, entity.getUser().getId());
        if (statement.getParameterMetaData().getParameterCount() == ID_INDEX) {
            statement.setLong(ID_INDEX, entity.getId());
        }
    }

    @Override
    protected String[] arrayOfEntityParameters(TicketOrder entity) {
        String[] result = new String[PARAMETER_NUMBERS_WITHOUT_ID];
        result[0] = PARAMETER_EMAIL;
        result[1] = PARAMETER_PAYMENT_METHOD;
        result[2] = PARAMETER_USER;
        return result;
    }
}
