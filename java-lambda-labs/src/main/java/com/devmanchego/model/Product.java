package com.devmanchego.model;

import java.math.BigDecimal;

public record Product(
    String id,
    String name,
    Category category,
    BigDecimal price,
    boolean active
) {}
