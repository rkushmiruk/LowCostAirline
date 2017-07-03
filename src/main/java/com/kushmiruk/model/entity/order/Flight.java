package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;
import com.kushmiruk.model.entity.location.Airport;

import java.sql.Date;

/**
 * Entity to table Flight
 */
public class Flight extends Entity implements Cloneable {
    private Long id;
    private Airport departureAirport;
    private Airport destinationAirport;
    private Date departureDateTime;
    private Date destinationDateTime;
    private Integer flightTime;
    private Integer totalSeatsNumber;

    public Flight(Flight flight) {
        this.id = flight.id;
        this.departureAirport = flight.departureAirport;
        this.destinationAirport = flight.destinationAirport;
        this.departureDateTime = flight.departureDateTime;
        this.destinationDateTime = flight.destinationDateTime;
        this.flightTime = flight.flightTime;
        this.totalSeatsNumber = flight.totalSeatsNumber;
    }

    private Flight(Builder builder) {
        this.id = builder.id;
        this.departureAirport = builder.departureAirport;
        this.destinationAirport = builder.destinationAirport;
        this.departureDateTime = builder.departureDateTime;
        this.destinationDateTime = builder.destinationDateTime;
        this.flightTime = builder.flightTime;
        this.totalSeatsNumber = builder.totalSeatsNumber;
    }

    /**
     * Builder for Flight entities
     */
    public static class Builder {
        private static final Integer MILLISECONDS_IN_MINUTE = 60000;
        private Long id;
        private Airport departureAirport;
        private Airport destinationAirport;
        private Date departureDateTime;
        private Date destinationDateTime;
        private Integer flightTime;
        private Integer totalSeatsNumber;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder departureAirport(Airport departureAirport) {
            this.departureAirport = departureAirport;
            return this;
        }

        public Builder destinationAirport(Airport destinationAirport) {
            this.destinationAirport = destinationAirport;
            return this;
        }

        public Builder departureDateTime(Date departureDateTime) {
            this.departureDateTime = departureDateTime;
            return this;
        }

        public Builder flightTime(Integer flightTime) {
            this.flightTime = flightTime;
            return this;
        }

        public Builder totalSeatsNumber(Integer totalSeatsNumber) {
            this.totalSeatsNumber = totalSeatsNumber;
            return this;
        }

        public Builder destinationDateTime() {
            this.destinationDateTime = countDestinationDateTime();
            return this;
        }

        public Flight build() {
            return new Flight(this);
        }

        private Date countDestinationDateTime() {
            return new Date(departureDateTime.getTime() +
                    MILLISECONDS_IN_MINUTE * (flightTime + destinationAirport.getCity().getTimeZone() - departureAirport.getCity().getTimeZone()));
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Date getDepartureDateTime() {
        return departureDateTime;
    }

    public void setDepartureDateTime(Date departureDateTime) {
        this.departureDateTime = departureDateTime;
    }

    public Date getDestinationDateTime() {
        return destinationDateTime;
    }

    public void setDestinationDateTime(Date destinationDateTime) {
        this.destinationDateTime = destinationDateTime;
    }

    public Integer getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Integer flightTime) {
        this.flightTime = flightTime;
    }

    public Integer getTotalSeatsNumber() {
        return totalSeatsNumber;
    }

    public void setTotalSeatsNumber(Integer totalSeatsNumber) {
        this.totalSeatsNumber = totalSeatsNumber;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Flight(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != null ? !id.equals(flight.id) : flight.id != null) return false;
        if (departureAirport != null ? !departureAirport.equals(flight.departureAirport) : flight.departureAirport != null)
            return false;
        if (destinationAirport != null ? !destinationAirport.equals(flight.destinationAirport) : flight.destinationAirport != null)
            return false;
        if (departureDateTime != null ? !departureDateTime.equals(flight.departureDateTime) : flight.departureDateTime != null)
            return false;
        if (destinationDateTime != null ? !destinationDateTime.equals(flight.destinationDateTime) : flight.destinationDateTime != null)
            return false;
        if (flightTime != null ? !flightTime.equals(flight.flightTime) : flight.flightTime != null) return false;
        return totalSeatsNumber != null ? totalSeatsNumber.equals(flight.totalSeatsNumber) : flight.totalSeatsNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 37 * result + (departureAirport != null ? departureAirport.hashCode() : 0);
        result = 37 * result + (destinationAirport != null ? destinationAirport.hashCode() : 0);
        result = 37 * result + (departureDateTime != null ? departureDateTime.hashCode() : 0);
        result = 37 * result + (destinationDateTime != null ? destinationDateTime.hashCode() : 0);
        result = 37 * result + (flightTime != null ? flightTime.hashCode() : 0);
        result = 37 * result + (totalSeatsNumber != null ? totalSeatsNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Flight{")
                .append("id=").append(id)
                .append(", departureAirport=").append(departureAirport)
                .append(", destinationAirport=").append(destinationAirport)
                .append(", departureDateTime=").append(departureDateTime)
                .append(", destinationDateTime=").append(destinationDateTime)
                .append(", flightTime=").append(flightTime)
                .append(", totalSeatsNumber=").append(totalSeatsNumber)
                .append('}').toString();
    }
}
