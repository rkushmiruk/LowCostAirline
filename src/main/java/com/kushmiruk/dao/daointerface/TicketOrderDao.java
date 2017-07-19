package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.order.TicketOrder;

import java.util.List;
import java.util.Optional;

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

    /**
     * Retrieves all ticket orders from database with pagination.
     *
     * @param start          start position
     * @param numbersOfItems number of items
     * @return List of entities
     */
    List<TicketOrder> findAll(Integer start, Integer numbersOfItems);
    
    /**
     * Get ticket order table size;
     * 
     * @return size
     */
    Optional<Integer> getTableSize();
}
