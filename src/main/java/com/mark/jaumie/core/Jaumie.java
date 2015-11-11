package com.mark.jaumie.core;

import com.mark.jaumie.objects.Button;
import com.mark.jaumie.objects.LED;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;

public class Jaumie extends Thread {

    public void run() {
        final GpioController controller;
        LED led1;
        Button button1;
        controller = GpioFactory.getInstance();

        led1 = new LED(controller);
        led1.led.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("Pin number: "+event.getPin()+" | Changed to: "+event.getState());
            }
        });

        button1 = new Button(controller);
        button1.button.addListener(new GpioPinListenerDigital() {
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                System.out.println("Pin number: "+event.getPin()+" | Changed to: "+event.getState());
            }
        });
        button1.button.addTrigger(new GpioSetStateTrigger(PinState.HIGH,led1.led, PinState.HIGH));
        button1.button.addTrigger(new GpioSetStateTrigger(PinState.LOW,led1.led, PinState.LOW));

        System.out.println("------ System Ready.. ------");

        for (;;){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
