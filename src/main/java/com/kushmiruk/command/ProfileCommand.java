package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.TicketOrder;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.service.TicketOrderService;
import com.kushmiruk.util.Pages;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kushmiruk.util.Parameters;

/**
 * Command which finds all user ticket orders;
 */
public class ProfileCommand implements Command {
    private TicketOrderService ticketOrderService;

    public ProfileCommand(TicketOrderService ticketOrderService) {
        this.ticketOrderService = ticketOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        UserAuthentication userAuth = (UserAuthentication) request.getSession().getAttribute(Parameters.USER_AUTH);
        List<TicketOrder> orders = ticketOrderService.getHistory(userAuth.getLogin());
        request.getSession().setAttribute(Parameters.ORDERS, orders);
        return Pages.PROFILE_PAGE;
    }

}
