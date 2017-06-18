package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;
import com.kushmiruk.model.entity.user.User;

/**
 * Entity to table TicketOrder
 */
public class TicketOrder extends Entity implements Cloneable {
    private Long id;
    private String email;
    private PaymentMethod paymentMethod;
    private User user;

    public TicketOrder() {
    }

    public TicketOrder(TicketOrder ticketOrder) {
        this.id = ticketOrder.id;
        this.email = ticketOrder.email;
        this.paymentMethod = ticketOrder.paymentMethod;
        this.user = ticketOrder.user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new TicketOrder(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketOrder that = (TicketOrder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (paymentMethod != that.paymentMethod) return false;
        return user != null ? user.equals(that.user) : that.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 23 * result + (email != null ? email.hashCode() : 0);
        result = 23 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 23 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TicketOrder{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", paymentMethod=" + paymentMethod +
                ", user=" + user +
                '}';
    }
}
