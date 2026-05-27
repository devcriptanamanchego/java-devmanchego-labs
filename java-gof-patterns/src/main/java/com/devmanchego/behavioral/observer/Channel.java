package com.devmanchego.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(String message) {
        observers.forEach(observer -> observer.update(message));
    }
}
