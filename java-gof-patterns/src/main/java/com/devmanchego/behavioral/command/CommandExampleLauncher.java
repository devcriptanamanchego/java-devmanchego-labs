package com.devmanchego.behavioral.command;

public class CommandExampleLauncher {

    public static void main(String[] args) {
        Light light = new Light();

        Command command = new LightOnCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.submit(command);
    }
}