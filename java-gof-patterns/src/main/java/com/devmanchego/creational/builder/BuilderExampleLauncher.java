package com.devmanchego.creational.builder;


public class BuilderExampleLauncher {

    public static void main(String[] args) {
        User user = new User.Builder()
                .name("Billy")
                .age(30)
                .email("billy@example.com")
                .build();

        System.out.println(user);
    }
}