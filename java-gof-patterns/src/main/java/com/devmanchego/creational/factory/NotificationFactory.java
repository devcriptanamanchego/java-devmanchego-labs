package com.devmanchego.creational.factory;


public class NotificationFactory {

    public Notification create(NotificationType notificationType) {
        return switch (notificationType) {
	        case EMAIL -> new EmailNotification();
	        case SMS -> new SmsNotification();
            default -> throw new IllegalArgumentException("Unknown type: " + notificationType);
        };
    }
}