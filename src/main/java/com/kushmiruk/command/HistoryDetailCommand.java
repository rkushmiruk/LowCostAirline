package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.Ticket;
import com.kushmiruk.service.TicketOrderService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Get detail information about order
 */
public class HistoryDetailCommand implements Command {
    private TicketOrderService ticketOrderService;

    public HistoryDetailCommand(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        Long orderId = Long.parseLong(request.getParameter(Parameters.ORDER_ID));
        List<Ticket> tickets = ticketOrderService.getOrderDetails(orderId);
        request.getSession().setAttribute(Parameters.ORDER_TICKETS, tickets);
        return Pages.HISTORY_PAGE;
    }

}
