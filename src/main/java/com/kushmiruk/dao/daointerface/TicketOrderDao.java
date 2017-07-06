package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.order.TicketOrder;

import java.util.List;


/**
 * DAO interface for CRUD operations with entity TicketOrder
 */
public interface TicketOrderDao extends GenericDao<TicketOrder, Long> {

    /**
     * Retrieves all ticket orders from database by user login;
     *
     * @param login user login
     * @return list of user ticket orders;
     */
    List<TicketOrder> findTicketOrdersByLogin(String login);
}
