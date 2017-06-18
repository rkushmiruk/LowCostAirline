package com.kushmiruk.model.entity.order;

/**
 * List of payment Methods
 */
public enum PaymentMethod {
    CREDITCARD, PAYPAL, CASH;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
