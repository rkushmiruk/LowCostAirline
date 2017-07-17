package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.Ticket;
import com.kushmiruk.model.entity.order.TicketOrder;

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
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

    /**
     * Get ticket details to current order
     *
     * @param ticketOrderId identifier of ticket order
     * @return List of ticket in current order
     */
    public List<Ticket> getOrderDetails(Long ticketOrderId) {
        List<Ticket> value;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketDao ticketDao = daoFactory.createTicketDao();
            value = ticketDao.getOrderDetails(ticketOrderId);
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

    /**
     * Retrieves all ticket orders from database with pagination.
     *
     * @param start          start position
     * @param numbersOfItems number of items
     * @return List of ticket orders
     */
    public List<TicketOrder> getAllOrders(Integer start, Integer numbersOfItems) {
        List<TicketOrder> value;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketOrderDao ticketOrderDao = daoFactory.createTicketOrderDao();
            value = ticketOrderDao.findAll(start, numbersOfItems);
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

    /**
     * Retrieves all ticket orders from database.
     *
     * @return List of ticket orders
     */
    public List<TicketOrder> getAll() {
        List<TicketOrder> value;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketOrderDao ticketOrderDao = daoFactory.createTicketOrderDao();
            value = ticketOrderDao.findAll();
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }
}
