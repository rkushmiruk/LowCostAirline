package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.Flight;

import com.kushmiruk.service.FlightService;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.LoggerMessage;
import com.kushmiruk.util.Pages;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kushmiruk.util.Parameters;
import org.apache.log4j.Logger;

/**
 * Command which finds all flights by departure city,destination city and date
 */
public class FlightListCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FlightListCommand.class);
    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private FlightService flightService = serviceFactory.createFlightService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        String cityFrom = request.getParameter(Parameters.FROM_CITY);
        String cityTo = request.getParameter(Parameters.TO_CITY);
        String date = request.getParameter(Parameters.DATE);
        request.getSession().setAttribute(Parameters.FROM_CITY, cityFrom);
        request.getSession().setAttribute(Parameters.TO_CITY, cityTo);
        request.getSession().setAttribute(Parameters.DATE, date);
        LOGGER.info(cityFrom);

        List<Flight> flights = flightService.searchFlights(cityFrom, cityTo, date);
        LOGGER.info(LoggerMessage.FLIGHTS + flights);
        request.getSession().setAttribute(Parameters.FLIGHTS, flights);
        return Pages.FLIGHTS_PAGE;
    }

    @Override
    public String doOnError(HttpServletRequest request, Exception e) throws AppException {
        LOGGER.error(e.getMessage());
        request.setAttribute(Parameters.EXCEPTION, e.getMessage());
        return Pages.INDEX_PAGE;
    }

}
