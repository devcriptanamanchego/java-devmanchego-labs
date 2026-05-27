package com.devmanchego.behavioral.observer;

public class UserObserver implements Observer {

    private final String name;

    public UserObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}