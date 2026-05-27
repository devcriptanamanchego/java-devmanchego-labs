package com.devmanchego.structural.adapter;

public class ChargerAdapter implements UsbCharger {

    private final OldCharger oldCharger;

    public ChargerAdapter(OldCharger oldCharger) {
        this.oldCharger = oldCharger;
    }

    @Override
    public void charge() {
        oldCharger.connectOldCable();
    }
}