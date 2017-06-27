package com.kushmiruk.model.entity.user;

/**
 * User role in application
 */
public enum UserRole {
    ADMIN(1L), USER(2L);

    private Long id;

    UserRole(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
