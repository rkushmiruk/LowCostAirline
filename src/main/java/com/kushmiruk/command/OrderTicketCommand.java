package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.*;
import com.kushmiruk.model.entity.user.UserAuthentication;

import com.kushmiruk.service.TicketService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Command create order and block tickets
 */
public class OrderTicketCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(OrderTicketCommand.class);
    private TicketService ticketService;

    public OrderTicketCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        List<Ticket> orderTickets = createAndBlockTicketsFromRequest(request, response);
        request.getSession().setAttribute(Parameters.ORDER_TICKETS, orderTickets);
        ticketService.getTicketsBlocked(orderTickets);
        return Pages.ORDER_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.TICKET_PAGE;
    }

    private List<Ticket> createAndBlockTicketsFromRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Ticket> orderTickets = new ArrayList<>();
        Flight flight = (Flight) request.getSession().getAttribute(Parameters.CURRENT_FLIGHT);
        Integer daysBeforeFlight = ticketService.daysBetween(new Date(new java.util.Date().getTime()),
                flight.getDepartureDateTime());
        ExtraPrice extraPrice = ticketService.getExtraPrice(daysBeforeFlight);
        UserAuthentication userAuth = (UserAuthentication) request.getSession().getAttribute(Parameters.USER_AUTH);
        TicketOrder ticketOrder = ticketService.getTicketOrder(userAuth);
        Long totalPrice = 0L;
        for (int i = 1; i <= Integer.parseInt(request.getParameter(Parameters.COUNT_TICKET)); i++) {
            String firstName = request.getParameter(Parameters.FIRST_NAME + i);
            String lastName = request.getParameter(Parameters.LAST_NAME + i);
            String email = request.getParameter(Parameters.EMAIL + i);
            Baggage baggage = Baggage.getById(Long.parseLong(request.getParameter(Parameters.HAS_BAGGAGE + i)));
            Boolean hasPriorityRegistration = ticketService.checkPriorityRegistration(request.getParameter(Parameters.HAS_PRIORITY_REGISTRATION + i));
            String seatNumber = request.getParameter(Parameters.SEAT_NUMBER + i);
            Ticket ticket = new Ticket.Builder()
                    .flight(flight)
                    .passengerFirstName(firstName)
                    .passengerLastName(lastName)
                    .email(email)
                    .baggage(baggage)
                    .hasPriorityRegistration(hasPriorityRegistration)
                    .seatNumber(Integer.parseInt(seatNumber))
                    .ticketStatus(TicketStatus.BLOCKED)
                    .extraPrice(extraPrice)
                    .ticketOrder(ticketOrder)
                    .build();
            Long price = ticketService.countTicketCost(ticket, flight, extraPrice);
            ticket.setPrice(price);
            orderTickets.add(ticket);
            totalPrice += price;
            request.getSession().setAttribute(Parameters.TOTAL_PRICE, totalPrice);
        }
        return orderTickets;
    }
}
