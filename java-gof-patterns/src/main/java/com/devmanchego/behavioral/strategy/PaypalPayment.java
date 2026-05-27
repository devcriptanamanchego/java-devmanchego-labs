package com.devmanchego.behavioral.strategy;

public class PaypalPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Paid with PayPal: " + amount);
    }
}