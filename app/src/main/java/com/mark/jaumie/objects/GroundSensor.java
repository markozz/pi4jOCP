package com.mark.jaumie.objects;

import com.mark.jaumie.triggers.CarDetectedTrigger;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;

public class GroundSensor {
    private static final Logger LOG = Logger.getLogger(GroundSensor.class);
    private static final GpioController gpio = GpioFactory.getInstance();

    private final GpioPinDigitalInput button;

    public GroundSensor(Pin pinToListenTo) {
        button = gpio.provisionDigitalInputPin(pinToListenTo, "Button", PinPullResistance.PULL_DOWN);
    }

    public void initialize() {
        // Add logger for button
        setConsoleLoggerFor(button);
        LOG.info("Ground sensor initialized");
    }

    public void addTrigger(CarDetectedTrigger trigger) {
        button.addTrigger(trigger);
    }

    private void setConsoleLoggerFor(GpioPinDigitalInput button) {
        button.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                LOG.debug("Pin number: " + event.getPin() + " | Changed to: " + event.getState());
            }
        });
    }
}
