package com.devmanchego.model;

import java.time.LocalDate;
import java.util.List;

public record Order(
    String id,
    Customer customer,
    LocalDate orderDate,
    List<Product> products,
    OrderStatus status
) {}
