package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.Ticket;
import com.kushmiruk.service.TicketService;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class OrderTicketCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(OrderTicketCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private TicketService ticketService = serviceFactory.createTicketService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        List<Ticket> orderTickets = new ArrayList<>();
        Set<Integer> seatNumbers = new TreeSet<>();

        for (int i = 1; i <= Integer.parseInt(request.getParameter(Parameters.COUNT_TICKET)); i++) {
            String firstName = request.getParameter(Parameters.FIRST_NAME + i);
            String lastName = request.getParameter(Parameters.LAST_NAME + i);
            String email = request.getParameter(Parameters.EMAIL + i);
            Boolean hasBaggage = Boolean.valueOf(request.getParameter(Parameters.HAS_BAGGAGE + i));
            Boolean hasPriorityRegistration = false;
            if (request.getParameter(Parameters.HAS_PRIORITY_REGISTRATION + i) != null
                    && request.getParameter(Parameters.HAS_PRIORITY_REGISTRATION + i).equals(Parameters.ON)) {
                hasPriorityRegistration = true;
            }
            String seatNumber = request.getParameter(Parameters.SEAT_NUMBER + i);
            seatNumbers.add(Integer.parseInt(seatNumber));
            if (seatNumbers.size() != i) {
                throw new AppException(ExceptionMessage.getMessage(ExceptionMessage.UNIQUE_SEAT_NUMBER_ERROR));
            }
            Ticket ticket = new Ticket.Builder()
                    .passengerFirstName(firstName)
                    .passengerLastName(lastName)
                    .email(email)
                    .hasBaggage(hasBaggage)
                    .hasPriorityRegistration(hasPriorityRegistration)
                    .seatNumber(Integer.parseInt(seatNumber))
                    .build();
            ticketService.checkTicketInput(ticket);
            LOGGER.info(ticket);
            orderTickets.add(ticket);
        }

        request.setAttribute(Parameters.ORDER_TICKETS, orderTickets);
        return Pages.ORDER_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.TICKET_PAGE;
    }

}
