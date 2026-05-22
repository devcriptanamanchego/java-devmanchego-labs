package com.devmanchego.creational.singleton;


public class SingletonExampleLauncher {

    public static void main(String[] args) {
        AppConfig config1 = AppConfig.getInstance();
        AppConfig config2 = AppConfig.getInstance();

        System.out.println(config1 == config2);
        System.out.println(config1.getApplicationName());
    }
}