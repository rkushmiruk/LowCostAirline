package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.order.Ticket;

import java.util.Optional;

/**
 * DAO interface for CRUD operations with entity Ticket
 */
public interface TicketDao extends GenericDao<Ticket, Long> {
    /**
     * Retrieve ticket status from database identified by id.
     *
     * @param id identifier of ticket
     * @return ticket status
     */
    Optional<String> findTicketStatus(Long id);
}
