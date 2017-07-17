package com.kushmiruk.model.entity.order;

public enum Baggage {
    NO(1L, 0L), LOW(2L, 5L), MEDIUM(3L, 7L), BIG(4L, 10L);

    private Long id;
    private Long price;

    Baggage(Long id, Long price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getPrice() {
        return price;
    }

    public static Baggage getById(Long id) {
        for (Baggage e : values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
