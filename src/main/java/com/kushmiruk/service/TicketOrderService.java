package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.model.entity.order.TicketOrder;

/**
 *  Service for interact with DAO layer interface TicketOrderDao
 */
public class TicketOrderService extends GenericService<TicketOrder> {
    private static TicketOrderDao ticketOrderDao = daoFactory.createTicketOrderDao();

    private TicketOrderService() {
        super(ticketOrderDao);
    }

    private static class TicketOrderServiceHolder {
        private static final TicketOrderService instance = new TicketOrderService();
    }

    public static TicketOrderService getInstance() {
        return TicketOrderServiceHolder.instance;
    }
}
