package com.devmanchego.model;

public record Customer(
    String id,
    String name,
    String email,
    String country
) {}
