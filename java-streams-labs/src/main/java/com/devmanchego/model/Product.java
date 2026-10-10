package com.devmanchego.model;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(
    String id,
    String name,
    Category category,
    BigDecimal price,
    boolean active
) {
    public Product {
        Objects.requireNonNull(id, "Product ID cannot be null");
        Objects.requireNonNull(name, "Product name cannot be null");
        Objects.requireNonNull(category, "Category cannot be null");
        Objects.requireNonNull(price, "Price cannot be null");
        
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}