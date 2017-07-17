package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.Ticket;
import com.kushmiruk.model.entity.order.TicketStatus;
import com.kushmiruk.service.TicketService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command which ends transaction with buying tickets
 */
public class BuyTicketCommand implements Command {
    private TicketService ticketService;

    public BuyTicketCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        List<Ticket> tickets = (List) request.getSession().getAttribute(Parameters.ORDER_TICKETS);
        String address = request.getParameter(Parameters.ADDRESS);
        tickets.forEach((Ticket ticket) -> {
            ticket.setTicketStatus(TicketStatus.PAID);
            ticket.setId(ticketService.findTicketId(ticket).get());
            ticketService.update(ticket);
        });
        request.setAttribute(Parameters.ADDRESS, address);
        return Pages.INDEX_PAGE;
    }
}
