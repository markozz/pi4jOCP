package com.mark.jaumie.objects;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.apache.log4j.Logger;

import java.util.concurrent.Future;

public class TrafficLight {
    private static final Logger LOG = Logger.getLogger(TrafficLight.class);

    private final GpioPinDigitalOutput red;
    private final GpioPinDigitalOutput yellow;
    private final GpioPinDigitalOutput green;
    private TrafficLightState state;

    public TrafficLight(GpioController gpio, Pin redLightPin, Pin yellowLightPin,Pin greenLightPin) {
        this.red = gpio.provisionDigitalOutputPin(redLightPin, "Red", PinState.LOW);
        this.yellow = gpio.provisionDigitalOutputPin(yellowLightPin, "Yellow", PinState.LOW);
        this.green = gpio.provisionDigitalOutputPin(greenLightPin, "Green", PinState.LOW);
        this.state = TrafficLightState.UNINITIALIZED;
    }

    /**
     * Method that will initialize the trafficlight.
     * 1. Adds loggers for each light
     * 2. Turns trafficlight into default state (close state)
     */
    public void initialize() {
        // Set shutdown states
        red.setShutdownOptions(true, PinState.LOW);
        yellow.setShutdownOptions(true, PinState.LOW);
        green.setShutdownOptions(true, PinState.LOW);

        // Add loggers for leds
        setConsoleLoggerFor(red);
        setConsoleLoggerFor(yellow);
        setConsoleLoggerFor(green);

        // Set trafficlight to red
        red.high();
        yellow.low();
        green.low();
        state = TrafficLightState.RED;

        LOG.info("Traffic light initialized");
    }

    /**
     * Return the current state of the traffic light
     * @returns TrafficLightState state
     */
    public TrafficLightState getState() {
        return state;
    }

    /**
     * Method for changing the traffic light to green
     */
    public Thread goGreen() {
        Thread goGreen = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // turn on green led, turn off all others
                    red.low();
                    yellow.high();
                    green.low();
                    state = TrafficLightState.IN_BETWEEN;
                    Thread.sleep(1000);
                    yellow.low();
                    green.high();
                    state = TrafficLightState.GREEN;
                    Thread.sleep(5000);
                    goRed();
                } catch (InterruptedException e) {
                    LOG.error("Unable to change trafficlight to green");
                    red.low();
                    yellow.low();
                    green.low();
                    state = TrafficLightState.MALFUNCTION;
                }
            }
        });

        goGreen.start();
        return goGreen;
    }

    /**
     * Method for changing the traffic light to red
     */
    public void goRed() {
        Thread goRed = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    // turn on red led, turn off all others
                    red.low();
                    yellow.high();
                    green.low();
                    state = TrafficLightState.IN_BETWEEN;
                    Thread.sleep(1000);
                    yellow.low();
                    red.high();
                    state = TrafficLightState.RED;
                } catch (InterruptedException e) {
                    LOG.error("Unable to change trafficlight to red");
                    goMalfunction();
                }
            }
        });

        goRed.start();
    }

    public void goMalfunction() {
        red.low();
        yellow.blink(300);
        green.low();
        state = TrafficLightState.MALFUNCTION;
    }

    private void setConsoleLoggerFor(GpioPinDigitalOutput led) {
        led.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                LOG.debug("Pin number: " + event.getPin() + " | Changed to: " + event.getState());
            }
        });
    }
}
