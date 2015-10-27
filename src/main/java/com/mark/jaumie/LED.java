package com.mark.jaumie;

/**
 * Created by mark on 19-10-15.
 */
public class LED {
    private boolean state;

    @Device(
            state = false,
            name = "LED1"
    )
    public LED() {
        this.state = false;
    }

    public void turnOn() {
        this.state = true;
        System.out.println("turned lights on");
    }

    public void turnOff() {
        this.state = false;
        System.out.println("turned lights off");
    }

    public boolean getState() {
        return state;
    }
}