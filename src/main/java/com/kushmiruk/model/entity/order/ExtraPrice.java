package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;

import java.sql.Date;

/**
 * Entity to table ExtraPrice
 */
public class ExtraPrice extends Entity implements Cloneable {
    private Long id;
    private Integer priorityRegistrationPrice;
    private Date purchaseDateTime;

    public ExtraPrice(Long id, Integer priorityRegistrationPrice, Date purchaseDateTime) {
        this.id = id;
        this.priorityRegistrationPrice = priorityRegistrationPrice;
        this.purchaseDateTime = purchaseDateTime;
    }

    public ExtraPrice(ExtraPrice extraPrice) {
        this.id = extraPrice.id;
        this.priorityRegistrationPrice = extraPrice.priorityRegistrationPrice;
        this.purchaseDateTime = extraPrice.purchaseDateTime;
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

    public Date getPurchaseDateTime() {
        return purchaseDateTime;
    }

    public void setPurchaseDateTime(Date purchaseDateTime) {
        this.purchaseDateTime = purchaseDateTime;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new ExtraPrice(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtraPrice that = (ExtraPrice) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (priorityRegistrationPrice != null ? !priorityRegistrationPrice.equals(that.priorityRegistrationPrice) : that.priorityRegistrationPrice != null)
            return false;
        return purchaseDateTime != null ? purchaseDateTime.equals(that.purchaseDateTime) : that.purchaseDateTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 43 * result + (priorityRegistrationPrice != null ? priorityRegistrationPrice.hashCode() : 0);
        result = 43 * result + (purchaseDateTime != null ? purchaseDateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExtraPrice{" +
                "id=" + id +
                ", priorityRegistrationPrice=" + priorityRegistrationPrice +
                ", purchaseDateTime=" + purchaseDateTime +
                '}';
    }
}
