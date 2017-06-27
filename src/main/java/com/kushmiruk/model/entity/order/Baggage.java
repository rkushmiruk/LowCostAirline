package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table Baggage
 */
public class Baggage extends Entity implements Cloneable {
    private Long id;
    private Integer weight;
    private Integer amount;
    private Integer price;
    private Ticket ticket;

    private Baggage(Builder builder) {
        this.id = id;
        this.weight = weight;
        this.amount = amount;
        this.price = price;
        this.ticket = ticket;
    }

    public Baggage(Long id, Integer weight, Integer amount, Integer price, Ticket ticket) {
        this.id = id;
        this.weight = weight;
        this.amount = amount;
        this.price = price;
        this.ticket = ticket;
    }

    public Baggage(Baggage baggage) {
        this.id = baggage.id;
        this.weight = baggage.weight;
        this.amount = baggage.amount;
        this.price = baggage.price;
        this.ticket = baggage.ticket;
    }

    /**
     * Builder for Baggage entities
     */
    public static class Builder {
        private static final Integer BAGGAGE_RATE = 2;
        private Long id;
        private Integer weight;
        private Integer amount;
        private Integer price;
        private Ticket ticket;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder weight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public Builder price(Integer price) {
            this.price = (amount + weight) / BAGGAGE_RATE;
            return this;
        }

        public Builder ticket(Ticket ticket) {
            this.ticket = ticket;
            return this;
        }

        public Baggage build() {
            return new Baggage(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = (amount + weight) * 20;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Baggage(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Baggage baggage = (Baggage) o;

        if (id != null ? !id.equals(baggage.id) : baggage.id != null) return false;
        if (weight != null ? !weight.equals(baggage.weight) : baggage.weight != null) return false;
        if (amount != null ? !amount.equals(baggage.amount) : baggage.amount != null) return false;
        if (price != null ? !price.equals(baggage.price) : baggage.price != null) return false;
        return ticket != null ? ticket.equals(baggage.ticket) : baggage.ticket == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 59 * result + (weight != null ? weight.hashCode() : 0);
        result = 59 * result + (amount != null ? amount.hashCode() : 0);
        result = 59 * result + (price != null ? price.hashCode() : 0);
        result = 59 * result + (ticket != null ? ticket.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Baggage{" +
                "id=" + id +
                ", weight=" + weight +
                ", amount=" + amount +
                ", price=" + price +
                ", ticket=" + ticket +
                '}';
    }
}
