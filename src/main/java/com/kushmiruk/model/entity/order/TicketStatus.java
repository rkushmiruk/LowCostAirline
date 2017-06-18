package com.kushmiruk.model.entity.order;

/**
 * List of ticket status;
 */
public enum TicketStatus {
    OPENED, BLOCKED, PAID;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
