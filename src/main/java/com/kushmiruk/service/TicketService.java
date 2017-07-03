package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.TicketDao;
import com.kushmiruk.model.entity.order.Ticket;

/**
 *  Service for interact with DAO layer interface TicketDao
 */
public class TicketService extends GenericService<Ticket> {
    private static TicketDao ticketDao = daoFactory.createTicketDao();

    private TicketService() {
        super(ticketDao);
    }

    private static class TicketServiceHolder {
        private static final TicketService instance = new TicketService();
    }

    public static TicketService getInstance() {
        return TicketServiceHolder.instance;
    }
}
