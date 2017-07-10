package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.Flight;
import com.kushmiruk.service.FlightService;
import com.kushmiruk.service.TicketService;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * It checks user's credentials and lets him to sign in
 */
public class TicketCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(TicketCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private TicketService ticketService = serviceFactory.createTicketService();
    private FlightService flightService = serviceFactory.createFlightService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        Long id = Long.parseLong(request.getParameter(Parameters.ID));
        Flight flight = flightService.findFlight(id).get();
        Set<Integer> freeTickets = ticketService.freeTickets(flight);
        request.getSession().setAttribute(Parameters.FREE_TICKETS, freeTickets);
        Integer countTicket = ticketService.parseInteger(request.getParameter(Parameters.COUNT));
        request.getSession().setAttribute(Parameters.COUNT_TICKET, countTicket);
        LOGGER.info(id);
        LOGGER.info(countTicket);
        return Pages.TICKET_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.FLIGHTS_PAGE;
    }


}
