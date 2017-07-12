package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;
import com.kushmiruk.model.entity.user.User;
import java.sql.Date;

/**
 * Entity to table TicketOrder
 */
public class TicketOrder extends Entity {
    private Long id;
    private PaymentMethod paymentMethod;
    private User user;
    private Date dateTime;

    public TicketOrder(Long id,PaymentMethod paymentMethod, User user,Date dateTime) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.user = user;
        this.dateTime = dateTime;
    }
    
    public TicketOrder(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TicketOrder(TicketOrder ticketOrder) {
        this.id = ticketOrder.id;
        this.paymentMethod = ticketOrder.paymentMethod;
        this.user = ticketOrder.user;
        this.dateTime = ticketOrder.dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketOrder that = (TicketOrder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (paymentMethod != that.paymentMethod) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 23 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 23 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("TicketOrder{")
                .append("id=").append(id)
                .append(", paymentMethod=").append(paymentMethod)
                .append(", user=").append(user)
                .append(", dateTime=").append(dateTime)
                .append('}').toString();
    }
}
