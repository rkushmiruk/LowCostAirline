package com.kushmiruk.command;

import com.kushmiruk.exception.AppException;
import com.kushmiruk.model.entity.order.Flight;
import com.kushmiruk.service.FlightService;
import com.kushmiruk.service.factory.ServiceFactory;
import com.kushmiruk.util.Pages;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kushmiruk.util.StringConstants;
import org.apache.log4j.Logger;

public class FlightListCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FlightListCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        String cityFrom = request.getParameter(StringConstants.FROM_CITY);
        String cityTo = request.getParameter(StringConstants.TO_CITY);
        String date = request.getParameter(StringConstants.DATE);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        FlightService flightService = serviceFactory.createFlightService();

        List<Flight> flights = flightService.searchFlights(cityFrom, cityTo, date);
        request.setAttribute(StringConstants.FLIGHTS, flights);
        LOGGER.info(cityFrom + cityTo + date);

        return Pages.FLIGHTS_PAGE;
    }

}
