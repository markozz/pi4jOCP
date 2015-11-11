package com.mark.jaumie.objects;

import com.mark.jaumie.annotations.Device;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LED {
    private final GpioController controller;
    private String ledName = "LED #1";
    private Device.State state;
    public GpioPinDigitalOutput led;

    @Device(
            state = Device.State.OFF,
            prettyName = "LED1",
            id = 1
    )
    public LED(GpioController controller) {
        this.controller = controller;
        // Provision pin 13 as LED which is off
        led = controller.provisionDigitalOutputPin(RaspiPin.GPIO_02,ledName,PinState.LOW);
    }

    public void turnOn() {
        controller.setState(PinState.HIGH, led);
        state = Device.State.ON;
        System.out.println("Turned on light: "+ledName);
    }

    public void turnOff() {
        controller.setState(PinState.LOW, led);
        state = Device.State.OFF;
        System.out.println("Turned off light: "+ledName);
    }

    public Device.State getState() {
//        PinState pinState = controller.getState(led);
//        Device.State state = null;
//        if (pinState == PinState.LOW) {
//            state = Device.State.OFF;
//        } else if (pinState == PinState.HIGH) {
//            state = Device.State.ON;
//        }
        return state;
    }

    public String getPrettyName() {
        return ledName;
    }
}