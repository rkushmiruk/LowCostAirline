package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;

/**
 * Entity to table ExtraPrice
 */
public class ExtraPrice extends Entity {
    private Long id;
    private Integer priorityRegistrationPrice;
    private Integer daysBeforeFlight;

    public ExtraPrice(Long id, Integer priorityRegistrationPrice,Integer daysBeforeFlight) {
        this.id = id;
        this.priorityRegistrationPrice = priorityRegistrationPrice;
        this.daysBeforeFlight = daysBeforeFlight;
    }
    
     public ExtraPrice(Integer priorityRegistrationPrice, Integer daysBeforeFlight) {
        this.priorityRegistrationPrice = priorityRegistrationPrice;
        this.daysBeforeFlight = daysBeforeFlight;
    }

    public ExtraPrice(ExtraPrice extraPrice) {
        this.id = extraPrice.id;
        this.priorityRegistrationPrice = extraPrice.priorityRegistrationPrice;
        this.daysBeforeFlight = extraPrice.daysBeforeFlight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPriorityRegistrationPrice() {
        return priorityRegistrationPrice;
    }

    public void setPriorityRegistrationPrice(Integer priorityRegistrationPrice) {
        this.priorityRegistrationPrice = priorityRegistrationPrice;
    }

    public Integer getDaysBeforeFlight() {
        return daysBeforeFlight;
    }

    public void setDaysBeforeFlight(Integer daysBeforeFlight) {
        this.daysBeforeFlight = daysBeforeFlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraPrice that = (ExtraPrice) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (priorityRegistrationPrice != null ? !priorityRegistrationPrice.equals(that.priorityRegistrationPrice) : that.priorityRegistrationPrice != null)
            return false;
        return daysBeforeFlight != null ? daysBeforeFlight.equals(that.daysBeforeFlight) : that.daysBeforeFlight == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 43 * result + (priorityRegistrationPrice != null ? priorityRegistrationPrice.hashCode() : 0);
        result = 43 * result + (daysBeforeFlight != null ? daysBeforeFlight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("ExtraPrice{")
                .append("id=").append(id)
                .append(", priorityRegistrationPrice=").append(priorityRegistrationPrice)
                .append(", daysBeforeFlight=").append(daysBeforeFlight)
                .append('}').toString();
    }
}
