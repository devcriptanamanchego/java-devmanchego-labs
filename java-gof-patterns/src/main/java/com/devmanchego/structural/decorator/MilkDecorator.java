package com.devmanchego.structural.decorator;

public class MilkDecorator implements Coffee {

    private final Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public String description() {
        return coffee.description() + " + milk";
    }

    @Override
    public double price() {
        return coffee.price() + 0.5;
    }
}