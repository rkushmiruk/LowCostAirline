package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.Flight;
import com.kushmiruk.model.entity.order.Ticket;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.RegexPattern;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.sql.DataSource;

/**
 * Service for interact with DAO layer interface TicketDao
 */
public class TicketService {

    private TicketService() {
    }

    private static class TicketServiceHolder {
        private static final TicketService instance = new TicketService();
    }

    public static TicketService getInstance() {
        return TicketServiceHolder.instance;
    }

    /**
     * Find all tickets which already have sold
     *
     * @param flightId
     * @return list of sell tickets
     */
    public List<Integer> findAllSellTickets(Long flightId) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        List<Integer> value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketDao ticketDao = daoFactory.createTicketDao();
            value = ticketDao.findAllSellTicketsSeatNumbers(flightId);
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

    /**
     * Parse string input into Integer if operation is not successful throw new
     * DaoException
     *
     * @param input input string
     * @return integer from string
     */
    public Integer parseInteger(String input) {
        if (!input.matches(RegexPattern.NUMBER_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.NAME_PATTERN_ERROR));//TODO
        }
        return Integer.parseInt(input);
    }

    /**
     * Check ticket fields with regex if operation is not successful throw new
     * DaoException
     *
     * @param ticket ticket
     */
    public void checkTicketInput(Ticket ticket) {
        if (!ticket.getPassangerFirstName().matches(RegexPattern.NAME_PATTERN)
                || !ticket.getPassngerLastName().matches(RegexPattern.NAME_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.NAME_PATTERN_ERROR));
        }
        if (!ticket.getEmail().matches(RegexPattern.EMAIL_PATTERN)) {
            throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.EMAIL_PATTERN_ERROR));
        }
    }

    /**
     * Find all free tickets for current flight
     *
     * @param flight flight
     * @return set of free tickets
     */
    public Set<Integer> freeTickets(Flight flight) {
        Set<Integer> seatsNumber = new TreeSet<>();
        List<Integer> sellTickets = findAllSellTickets(flight.getId());
        for (int j = 1; j <= flight.getTotalSeatsNumber(); j++) {
            seatsNumber.add(j);
        }
        seatsNumber.removeAll(sellTickets);
        return seatsNumber;
    }

}
