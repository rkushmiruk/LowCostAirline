package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.ExtraPriceDao;
import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.dao.factory.DataSourceFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.ExtraPrice;
import com.kushmiruk.model.entity.order.Flight;
import com.kushmiruk.model.entity.order.PaymentMethod;
import com.kushmiruk.model.entity.order.Ticket;
import com.kushmiruk.model.entity.order.TicketOrder;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.Parameters;
import com.kushmiruk.util.RegexPattern;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import javax.sql.DataSource;

/**
 * Service for interact with DAO layer interface TicketDao
 */
public class TicketService {

    private static final int PRIORITY_REGISTRATION_PRICE = 30;
    private static final int PRICE_FACTOR = 20;

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
     * @param flightId identifier of flight
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
     * @param input  input string
     * @param flight current flight
     * @return integer from string
     */
    public Integer numberOfCountTickets(String input, Flight flight) {
        if (!input.matches(RegexPattern.NUMBER_PATTERN)) {
            throw new DaoException("Error input number of tickets");
        }
        Integer value = Integer.parseInt(input);

        if (value > freeTickets(flight).size()) {
            throw new DaoException("More than free tickets");
        }

        if (value > 10) {
            throw new DaoException("You cant order more than 10 tickets");
        }
        return value;
    }

    /**
     * Check ticket fields with regex if operation is not successful throw new
     * DaoException
     *
     * @param ticket ticket
     */
    public void checkTicketInput(Ticket ticket) {
        if (!ticket.getPassengerFirstName().matches(RegexPattern.NAME_PATTERN)
                || !ticket.getPassengerLastName().matches(RegexPattern.NAME_PATTERN)) {
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

    /**
     * save user to database
     *
     * @param tickets
     * @throws DaoException if operation is not successful
     */
    public void getTicketsBlocked(List<Ticket> tickets) throws DaoException {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        boolean value;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketDao ticketDao = daoFactory.createTicketDao();
            for (Ticket ticket : tickets) {
                checkTicketInput(ticket);
                checkSeatNumber(ticket, ticketDao);
                value = ticketDao.insert(ticket);
                if (!value) {
                    connection.rollback();
                    throw new DaoException(ExceptionMessage.getMessage(ExceptionMessage.LOGIN_EXIST_ERROR));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    /**
     * Retrieve id from extraPrice table
     *
     * @param days days before flight
     * @return Long id
     */
    public ExtraPrice getExtraPrice(Integer days) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            ExtraPriceDao extraPriceDao = daoFactory.createExtraPriceDao();

            ExtraPrice extraPrice = new ExtraPrice(PRIORITY_REGISTRATION_PRICE, days);
            boolean extraPriceInsert = extraPriceDao.insert(extraPrice);
            if (!extraPriceInsert) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.EXTRA_PRICE_INSERT_ERROR);
            }
            connection.commit();
            extraPrice.setId(extraPriceDao.findId().get());
            return extraPrice;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    /**
     * Retrieve id from ticketOrder table
     *
     * @param userAuthentication user credentials
     * @return Long id
     */
    public TicketOrder getTicketOrder(UserAuthentication userAuthentication) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.createUserService();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketOrderDao ticketOrderDao = daoFactory.createTicketOrderDao();
            TicketOrder ticketOrder = new TicketOrder(PaymentMethod.CASH);
            if (userAuthentication != null) {
                ticketOrder.setUser(userService.findUserByLogin(userAuthentication.getLogin()));
            }
            boolean ticketOrderInsert = ticketOrderDao.insert(ticketOrder);
            if (!ticketOrderInsert) {
                connection.rollback();
                throw new DaoException(ExceptionMessage.TICKER_ORDER_INSERT_ERROR);
            }
            connection.commit();
            ticketOrder.setId(ticketOrderDao.findId().get());
            return ticketOrder;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    /**
     * Count days before current flight
     *
     * @param startDate current date
     * @param endDate   date of flight
     * @return days
     */
    public int daysBetween(Date startDate, Date endDate) {
        return Days.daysBetween(new LocalDate(startDate.getTime()), new LocalDate(endDate.getTime())).getDays();
    }

    /**
     * Check priority registration in ticket
     *
     * @param parameter string param from request
     * @return true if client want priority registration
     */
    public Boolean checkPriorityRegistration(String parameter) {
        Boolean hasPriorityRegistration = false;
        if (parameter != null && parameter.equals(Parameters.ON)) {
            hasPriorityRegistration = true;
        }
        return hasPriorityRegistration;
    }

    /**
     * Count ticket cost like:
     * startPrice + priorityRegistrationPrice +20/freeTickets + 20/daysBeforeFlight
     * Price Factor = 20;
     *
     * @param ticket
     * @param flight
     * @param extraPrice
     * @return price
     */
    public Long countTicketCost(Ticket ticket, Flight flight, ExtraPrice extraPrice) {
        Long value;
        if (ticket.getHasPriorityRegistration()) {
            value = (long) extraPrice.getPriorityRegistrationPrice();
        } else {
            value = 0L;
        }
        value += flight.getStartPrice() + ticket.getBaggage().getPrice() + PRICE_FACTOR / extraPrice.getDaysBeforeFlight()
                + PRICE_FACTOR / freeTickets(flight).size();
        return value;
    }

    /**
     * Update ticket in a database.
     *
     * @param ticket entity to update
     */
    public void update(Ticket ticket) {
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketDao ticketDao = daoFactory.createTicketDao();
            Boolean value = ticketDao.update(ticket);
            if (!value) {
                connection.rollback();
                throw new DaoException("Error update");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }

    }

    /**
     * Retrieve id from ticket table
     *
     * @param ticket
     * @return Long id
     */
    public Optional<Long> findTicketId(Ticket ticket) {
        Optional<Long> value;
        DataSource dataSource = DataSourceFactory.getInstance().getDataSource();
        try (Connection connection = dataSource.getConnection()) {
            DaoFactory daoFactory = DaoFactory.getDaoFactory(connection);
            TicketDao ticketDao = daoFactory.createTicketDao();
            value = ticketDao.findTicketIdByFlightAndSeatNumber(ticket.getFlight().getId(), ticket.getSeatNumber());
            if (!value.isPresent()) {
                throw new DaoException("Can t find id");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return value;
    }

    /**
     * Check seat number in ticket
     *
     * @param ticket    ticket param from request
     * @param ticketDao dao with same connection
     */
    private void checkSeatNumber(Ticket ticket, TicketDao ticketDao) {
        Optional<Long> id = ticketDao.findTicketIdByFlightAndSeatNumber(ticket.getFlight().getId(), ticket.getSeatNumber());
        if (id.isPresent()) {
            throw new DaoException("This ticket ordered");
        }
    }

}
