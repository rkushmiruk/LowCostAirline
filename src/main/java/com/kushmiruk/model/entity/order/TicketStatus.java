package com.kushmiruk.model.entity.order;

/**
 * List of ticket status;
 */
public enum TicketStatus {
    OPENED(1L), BLOCKED(2L), PAID(3L);

    private Long id;

    TicketStatus(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
