package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.TicketOrder;
import com.kushmiruk.util.ExceptionMessage;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import javax.sql.DataSource;

/**
 * Service for interact with DAO layer interface TicketOrderDao
 */
public class TicketOrderService {

    private TicketOrderService() {
    }

    private static class TicketOrderServiceHolder {
        private static final TicketOrderService instance = new TicketOrderService();
    }

    public static TicketOrderService getInstance() {
        return TicketOrderServiceHolder.instance;
    }

    /**
     * Retrieves all ticket orders by user login;
     *
     * @param login user login
     * @return list of user ticket orders;
     */
    public List<TicketOrder> getHistory(String login) {
        List<TicketOrder> value;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketOrderDao ticketOrderDao = daoFactory.createTicketOrderDao();
            value = ticketOrderDao.findTicketOrdersByLogin(login);
            connection.commit();
            if (value.isEmpty()) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.ORDER_NOT_FOUND_ERROR));
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;

    }
}
