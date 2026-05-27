package com.devmanchego.behavioral.strategy;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid with credit card: " + amount);
    }
}