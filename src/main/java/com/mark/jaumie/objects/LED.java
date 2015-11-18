package com.mark.jaumie.objects;

import com.mark.jaumie.annotations.Device;
import com.pi4j.io.gpio.*;

import java.io.IOException;

public class LED {
    private String ledName = "LED #1";
    private Pin ledPin = RaspiPin.GPIO_02;

    public GpioPinDigitalOutput led;
    private final GpioController controller;

    public LED(GpioController controller) {
        this.controller = controller;
        // Provision pin 13 as LED which is off
        led = controller.provisionDigitalOutputPin(ledPin,ledName,PinState.LOW);
    }

    public void turnOn() {
        controller.setState(PinState.HIGH, led);
        System.out.println("Turned on light: "+ledName);
    }

    public void turnOff() {
        controller.setState(PinState.LOW, led);
        System.out.println("Turned off light: "+ledName);
    }

    public Device.State getState() throws IOException {
        PinState pinState = controller.getState(led);
        Device.State state = null;
        if (pinState == PinState.LOW) {
            state = Device.State.OFF;
        } else if (pinState == PinState.HIGH) {
            state = Device.State.ON;
        } else {
            throw new IOException("Could not define state from pin");
        }
        return state;
    }

    public String getPrettyName() {
        return ledName;
    }
}