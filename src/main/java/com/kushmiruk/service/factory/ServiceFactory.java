package com.kushmiruk.service.factory;

import com.kushmiruk.service.*;

/**
 * Factory for application services
 */
public class ServiceFactory {

    private ServiceFactory() {
    }

    private static class ServiceFactoryHolder {
        private static final ServiceFactory instance = new ServiceFactory();
    }

    public static ServiceFactory getInstance() {
        return ServiceFactoryHolder.instance;
    }

    public static AirportService createAirportDao() {
        return AirportService.getInstance();
    }

    public BaggageService createBaggageService() {
        return BaggageService.getInstance();
    }

    public CityService createCityService() {
        return CityService.getInstance();
    }

    public CountryService createCountryService() {
        return CountryService.getInstance();
    }

    public ExtraPriceService createExtraPriceService() {
        return ExtraPriceService.getInstance();
    }

    public FlightService createFlightService() {
        return FlightService.getInstance();
    }

    public TicketService createTicketService() {
        return TicketService.getInstance();
    }

    public TicketOrderService createTicketOrderService() {
        return TicketOrderService.getInstance();
    }

    public UserService createUserService() {
        return UserService.getInstance();
    }

    public UserAuthenticationService createUserAuthenticationService() {
        return UserAuthenticationService.getInstance();
    }

}
