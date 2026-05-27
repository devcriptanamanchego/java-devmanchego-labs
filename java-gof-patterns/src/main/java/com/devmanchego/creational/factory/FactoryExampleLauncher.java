package com.devmanchego.creational.factory;


public class FactoryExampleLauncher {

    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();

        Notification notification = factory.create(NotificationType.EMAIL);
        notification.send("Hello Factory Method");
    }
}
