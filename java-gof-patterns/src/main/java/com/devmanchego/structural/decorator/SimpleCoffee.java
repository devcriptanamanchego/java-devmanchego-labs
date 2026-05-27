package com.devmanchego.structural.decorator;

public class SimpleCoffee implements Coffee {

    @Override
    public String description() {
        return "Simple coffee";
    }

    @Override
    public double price() {
        return 2.0;
    }
}