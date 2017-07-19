package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.TicketOrder;
import com.kushmiruk.service.TicketOrderService;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command which finds all ticket orders in system;
 */
public class ProfileAdminCommand implements Command {
    private TicketOrderService ticketOrderService;
    private static final Integer NUMBER_OF_ITEMS = 10;
    private static final Integer START_POSITION = 0;
    private static final Integer STEP_BEFORE = -1;

    public ProfileAdminCommand(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        Integer allItems= ticketOrderService.getNumberOfItems();
        Integer numberOfPages = allItems / NUMBER_OF_ITEMS;
        if (allItems % NUMBER_OF_ITEMS > 0) {
            numberOfPages++;
        }
        String currentPage = request.getParameter(Parameters.PAGE);
        Integer currentItem = START_POSITION;
        if (currentPage != null && !currentPage.isEmpty()) {
            currentItem = (Integer.parseInt(currentPage) + STEP_BEFORE) * (NUMBER_OF_ITEMS);
        }
        List<TicketOrder> orders = ticketOrderService.getAllOrders(currentItem, NUMBER_OF_ITEMS);
        request.getSession().setAttribute(Parameters.NUMBER_OF_PAGES, numberOfPages);
        request.getSession().setAttribute(Parameters.CURRENT_PAGE, currentPage);
        request.getSession().setAttribute(Parameters.ORDERS, orders);
        return Pages.PROFILE_ADMIN_PAGE;
    }

}
