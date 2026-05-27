package com.devmanchego.behavioral.observer;

public class ObserverExampleLauncher {

    public static void main(String[] args) {
        Channel channel = new Channel();

        channel.subscribe(new UserObserver("Alice"));
        channel.subscribe(new UserObserver("Bob"));

        channel.notifyObservers("New video uploaded");
    }
}