package com.devmanchego.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public record Order(
    String id,
    Customer customer,
    LocalDate orderDate,
    List<Product> products,
    OrderStatus status
) {
    public Order {
        Objects.requireNonNull(id, "Order ID cannot be null");
        Objects.requireNonNull(customer, "Customer cannot be null");
        Objects.requireNonNull(orderDate, "Order date cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        
        // Guarantees deep immutability for the list component
        products = List.copyOf(products);
    }

    // Helper method using Streams to compute total order amount
    public BigDecimal totalAmount() {
        return products.stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}