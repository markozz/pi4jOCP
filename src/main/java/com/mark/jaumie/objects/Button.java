package com.mark.jaumie.objects;


import com.pi4j.io.gpio.*;

public class Button {
    private String buttonName = "Button #1";
    private Pin buttonPin = RaspiPin.GPIO_01;

    public GpioPinDigitalInput button;

    public Button(GpioController controller) {
        // Provision pin 12 to be a button
        button = controller.provisionDigitalInputPin(buttonPin,buttonName);
    }

    public boolean isPressed() {
        boolean state = false;
        if (button.getState()==PinState.HIGH) {
            state = true;
        }
        return state;
    }

    public String getName() {
        return buttonName;
    }
}