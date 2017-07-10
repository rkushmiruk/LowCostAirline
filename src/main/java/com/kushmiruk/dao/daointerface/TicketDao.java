package com.kushmiruk.dao.daointerface;

import com.kushmiruk.model.entity.order.Ticket;

import java.util.List;

import java.util.Optional;

/**
 * DAO interface for CRUD operations with entity Ticket
 */
public interface TicketDao extends GenericDao<Ticket, Long> {
    /**
     * Retrieve ticket STATUS from database identified by id.
     *
     * @param id identifier of ticket
     * @return ticket STATUS
     */
    Optional<String> findTicketStatus(Long id);

    /**
     * Retrieve list of all sell tickets seat numbers from database.
     *
     * @param flightId identifier of flight
     * @return list of sell tickets seat numbers
     */
    List<Integer> findAllSellTicketsSeatNumbers(Long flightId);
}
