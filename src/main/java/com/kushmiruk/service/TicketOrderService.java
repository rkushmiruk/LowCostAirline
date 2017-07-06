package com.kushmiruk.service;

import com.kushmiruk.dao.daointerface.TicketOrderDao;
import com.kushmiruk.dao.factory.DaoFactory;
import com.kushmiruk.exception.DaoException;
import com.kushmiruk.model.entity.order.TicketOrder;
import java.util.List;

/**
 * Service for interact with DAO layer interface TicketOrderDao
 */
public class TicketOrderService {
    private static final DaoFactory daoFactory = DaoFactory.getDaoFactory();
    private static TicketOrderDao ticketOrderDao = daoFactory.createTicketOrderDao();

    private TicketOrderService() {
    }

    private static class TicketOrderServiceHolder {
        private static final TicketOrderService instance = new TicketOrderService();
    }

    public static TicketOrderService getInstance() {
        return TicketOrderServiceHolder.instance;
    }
    
    public List<TicketOrder> getHistory(String login){
        List<TicketOrder> value = ticketOrderDao.findTicketOrdersByLogin(login);
        if (value.isEmpty()){
            throw new DaoException("No tickets order yet");
        }
        
        return value;
    }
}
