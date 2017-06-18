package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table Ticket
 */
public class Ticket extends Entity implements Cloneable {
    private Long id;
    private String passangerFirstName;
    private String passangerLastName;
    private String email;
    private Boolean hasPriorityRegistration;
    private Boolean hasBaggage;
    private Integer price;
    private Integer seatNumber;
    private TicketOrder ticketOrder;
    private Flight flight;
    private ExtraPrice extraPrice;

    public Ticket(Ticket ticket) {
        this.id = ticket.id;
        this.passangerFirstName = ticket.passangerFirstName;
        this.passangerLastName = ticket.passangerLastName;
        this.email = ticket.email;
        this.hasPriorityRegistration = ticket.hasPriorityRegistration;
        this.hasBaggage = ticket.hasBaggage;
        this.price = ticket.price;
        this.seatNumber = ticket.seatNumber;
        this.ticketOrder = ticket.ticketOrder;
        this.flight = ticket.flight;
        this.extraPrice = ticket.extraPrice;
    }

    private Ticket(Builder builder) {
        this.id = builder.id;
        this.passangerFirstName = builder.passangerFirstName;
        this.passangerLastName = builder.passangerLastName;
        this.email = builder.email;
        this.hasPriorityRegistration = builder.hasPriorityRegistration;
        this.hasBaggage = builder.hasBaggage;
        this.price = builder.price;
        this.seatNumber = builder.seatNumber;
        this.ticketOrder = builder.ticketOrder;
        this.flight = builder.flight;
        this.extraPrice = builder.extraPrice;
    }

    /**
     * Builder for Ticket entities
     */
    public static class Builder {
        private Long id;
        private String passangerFirstName;
        private String passangerLastName;
        private String email;
        private Boolean hasPriorityRegistration;
        private Boolean hasBaggage;
        private Integer price;
        private Integer seatNumber;
        private TicketOrder ticketOrder;
        private Flight flight;
        private ExtraPrice extraPrice;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder passangerFirstName(String passangerFirstName) {
            this.passangerFirstName = passangerFirstName;
            return this;
        }

        public Builder passangerLastName(String passangerLastName) {
            this.passangerLastName = passangerLastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder hasPriorityRegistration(Boolean hasPriorityRegistration) {
            this.hasPriorityRegistration = hasPriorityRegistration;
            return this;
        }

        public Builder hasBaggage(Boolean hasBaggage) {
            this.hasBaggage = hasBaggage;
            return this;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder seatNumber(Integer seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public Builder ticketOrder(TicketOrder ticketOrder) {
            this.ticketOrder = ticketOrder;
            return this;
        }

        public Builder flight(Flight flight) {
            this.flight = flight;
            return this;
        }

        public Builder extraPrice(ExtraPrice extraPrice) {
            this.extraPrice = extraPrice;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassangerFirstName() {
        return passangerFirstName;
    }

    public void setPassangerFirstName(String passangerFirstName) {
        this.passangerFirstName = passangerFirstName;
    }

    public String getPassngerLastName() {
        return passangerLastName;
    }

    public void setPassngerLastName(String passngerLastName) {
        this.passangerLastName = passngerLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getHasPriorityRegistration() {
        return hasPriorityRegistration;
    }

    public void setHasPriorityRegistration(Boolean hasPriorityRegistration) {
        this.hasPriorityRegistration = hasPriorityRegistration;
    }

    public Boolean getHasBaggage() {
        return hasBaggage;
    }

    public void setHasBaggage(Boolean hasBaggage) {
        this.hasBaggage = hasBaggage;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public TicketOrder getTicketOrder() {
        return ticketOrder;
    }

    public void setTicketOrder(TicketOrder ticketOrder) {
        this.ticketOrder = ticketOrder;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public ExtraPrice getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(ExtraPrice extraPrice) {
        this.extraPrice = extraPrice;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Ticket(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (id != null ? !id.equals(ticket.id) : ticket.id != null) return false;
        if (passangerFirstName != null ? !passangerFirstName.equals(ticket.passangerFirstName) : ticket.passangerFirstName != null)
            return false;
        if (passangerLastName != null ? !passangerLastName.equals(ticket.passangerLastName) : ticket.passangerLastName != null)
            return false;
        if (email != null ? !email.equals(ticket.email) : ticket.email != null) return false;
        if (hasPriorityRegistration != null ? !hasPriorityRegistration.equals(ticket.hasPriorityRegistration) : ticket.hasPriorityRegistration != null)
            return false;
        if (hasBaggage != null ? !hasBaggage.equals(ticket.hasBaggage) : ticket.hasBaggage != null) return false;
        if (price != null ? !price.equals(ticket.price) : ticket.price != null) return false;
        if (seatNumber != null ? !seatNumber.equals(ticket.seatNumber) : ticket.seatNumber != null) return false;
        if (ticketOrder != null ? !ticketOrder.equals(ticket.ticketOrder) : ticket.ticketOrder != null) return false;
        if (flight != null ? !flight.equals(ticket.flight) : ticket.flight != null) return false;
        return extraPrice != null ? extraPrice.equals(ticket.extraPrice) : ticket.extraPrice == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 61 * result + (passangerFirstName != null ? passangerFirstName.hashCode() : 0);
        result = 61 * result + (passangerLastName != null ? passangerLastName.hashCode() : 0);
        result = 61 * result + (email != null ? email.hashCode() : 0);
        result = 61 * result + (hasPriorityRegistration != null ? hasPriorityRegistration.hashCode() : 0);
        result = 61 * result + (hasBaggage != null ? hasBaggage.hashCode() : 0);
        result = 61 * result + (price != null ? price.hashCode() : 0);
        result = 61 * result + (seatNumber != null ? seatNumber.hashCode() : 0);
        result = 61 * result + (ticketOrder != null ? ticketOrder.hashCode() : 0);
        result = 61 * result + (flight != null ? flight.hashCode() : 0);
        result = 61 * result + (extraPrice != null ? extraPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", passangerFirstName='" + passangerFirstName + '\'' +
                ", passangerLastName='" + passangerLastName + '\'' +
                ", email='" + email + '\'' +
                ", hasPriorityRegistration=" + hasPriorityRegistration +
                ", hasBaggage=" + hasBaggage +
                ", price=" + price +
                ", seatNumber=" + seatNumber +
                ", ticketOrder=" + ticketOrder +
                ", flight=" + flight +
                ", extraPrice=" + extraPrice +
                '}';
    }
}
