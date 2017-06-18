package com.kushmiruk.model.entity.user;

/**
 * User role in application
 */
public enum UserRole {
    ADMIN, USER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
