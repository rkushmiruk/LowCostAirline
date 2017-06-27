package com.kushmiruk.model.entity.location;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table City
 */
public class City extends Entity implements Cloneable {
    private Long id;
    private String name;
    private Integer timeZone;
    private Country country;

    public City(Long id, String name, Integer timeZone, Country country) {
        this.id = id;
        this.name = name;
        this.timeZone = timeZone;
        this.country = country;
    }

    public City(City city) {
        this.id = city.id;
        this.name = city.name;
        this.timeZone = city.timeZone;
        this.country = city.country;
    }

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

    public Integer getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Integer timeZone) {
        this.timeZone = timeZone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new City(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (id != null ? !id.equals(city.id) : city.id != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (timeZone != null ? !timeZone.equals(city.timeZone) : city.timeZone != null) return false;
        return country != null ? country.equals(city.country) : city.country == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (timeZone != null ? timeZone.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", timeZone=" + timeZone +
                ", country=" + country +
                '}';
    }
}
