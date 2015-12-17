package com.mark.jaumie.core;

import com.mark.jaumie.objects.GroundSensor;
import com.mark.jaumie.objects.TrafficLight;
import com.mark.jaumie.triggers.CarDetectedTrigger;
import com.pi4j.io.gpio.*;
import org.apache.log4j.Logger;

public class Jaumie extends Thread {

    private static final Logger LOG = Logger.getLogger(Jaumie.class);

    public void run() {
        final GpioController gpio = GpioFactory.getInstance();

        LOG.debug("Initializing system..");

        TrafficLight trafficLight = new TrafficLight(gpio,RaspiPin.GPIO_05,RaspiPin.GPIO_15,RaspiPin.GPIO_16);
        trafficLight.initialize();

        GroundSensor groundSensor = new GroundSensor(RaspiPin.GPIO_04);
        groundSensor.initialize();

        groundSensor.addTrigger(new CarDetectedTrigger(trafficLight));

        LOG.info("------ System Ready.. ------");
        for (; ; ) {
            try {
                sleep(500);
            } catch (InterruptedException e) {
                gpio.shutdown();
                return;
            }
        }
    }

}
