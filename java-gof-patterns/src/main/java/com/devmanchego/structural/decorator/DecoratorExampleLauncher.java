package com.devmanchego.structural.decorator;

public class DecoratorExampleLauncher {

    public static void main(String[] args) {
        Coffee coffee = new MilkDecorator(new SimpleCoffee());

        System.out.println(coffee.description());
        System.out.println(coffee.price());
    }
}