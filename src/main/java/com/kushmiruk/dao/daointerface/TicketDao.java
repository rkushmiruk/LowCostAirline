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
     * Retrieve ticket baggage from database identified by id.
     *
     * @param id identifier of baggage
     * @return baggage type
     */
    Optional<String> findTicketBaggage(Long id);

    /**
     * Retrieve list of all sell tickets seat numbers from database.
     *
     * @param flightId identifier of flight
     * @return list of sell tickets seat numbers
     */
    List<Integer> findAllSellTicketsSeatNumbers(Long flightId);

    /**
     * Retrieve ticket id by seat number and current flight from database.
     *
     * @param flightId   identifier of flight
     * @param seatNumber ticket seat number
     * @return id
     */
    Optional<Long> findTicketIdByFlightAndSeatNumber(Long flightId, Integer seatNumber);

    /**
     * Get ticket details to current order
     *
     * @param ticketOrderId identifier of ticket order
     * @return List of ticket in current order
     */
    List<Ticket> getOrderDetails(Long ticketOrderId);
}
