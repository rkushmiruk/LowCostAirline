package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.TicketOrder;
import com.kushmiruk.model.entity.user.UserAuthentication;
import com.kushmiruk.service.TicketOrderService;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.Pages;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kushmiruk.util.Parameters;
import org.apache.log4j.Logger;

/**
 * Command which finds all user ticket orders;
 */
public class ProfileCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ProfileCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private TicketOrderService ticketOrderService = serviceFactory.createTicketOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        UserAuthentication userAuth = (UserAuthentication) request.getSession().getAttribute(Parameters.USER_AUTH);
        List<TicketOrder> orders = ticketOrderService.getHistory(userAuth.getLogin());
        LOGGER.info(orders);
        request.setAttribute(Parameters.ORDERS, orders);
        return Pages.PROFILE_PAGE;
    }

}
