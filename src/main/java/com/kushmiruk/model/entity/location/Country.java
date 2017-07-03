package com.kushmiruk.model.entity.location;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table Country
 */
public class Country extends Entity implements Cloneable {
    private Long id;
    private String name;

    public Country(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Country(Country country) {
        this.id = country.id;
        this.name = country.name;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Country(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != country.id) return false;
        return name != null ? name.equals(country.name) : country.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 83 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Country{")
                .append("id=")
                .append(id)
                .append(", name='")
                .append(name)
                .append('\'')
                .append('}').toString();
    }
}
