package com.kushmiruk.model.entity.order;

public enum Baggage {
    NO(1L, 0), LOW(2L, 5), MEDIUM(3L, 7), BIG(4L, 10);

    private Long id;
    private Integer price;

    Baggage(Long id, Integer price) {
        this.id = id;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Integer getPrice() {
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
