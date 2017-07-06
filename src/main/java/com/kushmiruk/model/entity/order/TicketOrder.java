package com.kushmiruk.model.entity.order;

import com.kushmiruk.model.entity.Entity;
import com.kushmiruk.model.entity.user.User;
import java.sql.Date;

/**
 * Entity to table TicketOrder
 */
public class TicketOrder extends Entity implements Cloneable {
    private Long id;
    private String email;
    private PaymentMethod paymentMethod;
    private User user;
    private Date dateTime;

    public TicketOrder(Long id, String email, PaymentMethod paymentMethod, User user,Date dateTime) {
        this.id = id;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.user = user;
        this.dateTime = dateTime;
    }

    public TicketOrder(TicketOrder ticketOrder) {
        this.id = ticketOrder.id;
        this.email = ticketOrder.email;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
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
        return new StringBuilder()
                .append("TicketOrder{")
                .append("id=").append(id)
                .append(", EMAIL='").append(email)
                .append(", paymentMethod=").append(paymentMethod)
                .append(", user=").append(user)
                .append(", dateTime=").append(dateTime)
                .append('}').toString();
    }
}
