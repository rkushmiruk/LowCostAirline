package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.util.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command which ends transaction with buying tickets
 */
public class BuyTicketCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        return Pages.ORDER_PAGE;
    }
}
