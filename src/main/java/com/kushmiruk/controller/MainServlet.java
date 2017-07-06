package com.kushmiruk.controller;

import com.kushmiruk.command.CommandInvoker;
import com.kushmiruk.util.ExceptionMessage;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.Pages;
import com.kushmiruk.util.Parameters;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

/**
 * HTTP servlet for application
 */
public class MainServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainServlet.class);

    private static CommandInvoker commandInvoker = CommandInvoker.getInstance();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {

        String page;
        try {
            page = commandInvoker.invoke(request, response);
            request.getSession().setAttribute(Parameters.PAGE, page);
            LOGGER.info(LoggerMessage.OPEN_PAGE + page);
        } catch (RuntimeException e) {
            LOGGER.error(ExceptionMessage.ERROR_PAGE + LoggerMessage.EXCEPTION_MESSAGE + e.getMessage());
            request.setAttribute(ExceptionMessage.MESSAGE_ERROR, e.getMessage());
            request.setAttribute(ExceptionMessage.ERROR_DETAILS, e.getStackTrace());
            page = Pages.ERROR_PAGE;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
