package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.model.entity.order.Ticket;

/**
 * Service for interact with DAO layer interface TicketDao
 */
public class TicketService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static TicketDao ticketDao = daoFactory.createTicketDao();

    private TicketService() {
    }

    private static class TicketServiceHolder {
        private static final TicketService instance = new TicketService();
    }

    public static TicketService getInstance() {
        return TicketServiceHolder.instance;
    }
}
