package com.devmanchego.structural.adapter;

public class AdapterExampleLauncher {

    public static void main(String[] args) {
        UsbCharger charger = new ChargerAdapter(new OldCharger());
        charger.charge();
    }
}