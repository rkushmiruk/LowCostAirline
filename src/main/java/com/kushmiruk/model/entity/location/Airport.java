package com.kushmiruk.model.entity.location;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table Airport
 */
public class Airport extends Entity implements Cloneable {
    private Long id;
    private String name;
    private City city;

    public Airport() {
    }

    public Airport(Airport airport) {
        this.id = airport.id;
        this.name = airport.name;
        this.city = airport.city;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Airport(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        if (id != null ? !id.equals(airport.id) : airport.id != null) return false;
        if (name != null ? !name.equals(airport.name) : airport.name != null) return false;
        return city != null ? city.equals(airport.city) : airport.city == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 47 * result + (name != null ? name.hashCode() : 0);
        result = 47 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                '}';
    }
}
