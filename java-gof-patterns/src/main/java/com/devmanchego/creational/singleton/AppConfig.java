package com.devmanchego.creational.singleton;

public final class AppConfig {

    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public String getApplicationName() {
        return "GoF Example";
    }
}