package com.devmanchego.model;

import java.util.Objects;

public record Customer(
    String id,
    String name,
    String email,
    String country
) {
    public Customer {
        Objects.requireNonNull(id, "Customer ID cannot be null");
        Objects.requireNonNull(name, "Customer name cannot be null");
        Objects.requireNonNull(email, "Customer email cannot be null");
        Objects.requireNonNull(country, "Country cannot be null");
    }
}