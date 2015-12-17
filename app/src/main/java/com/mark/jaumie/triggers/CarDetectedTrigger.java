package com.mark.jaumie.triggers;

import com.mark.jaumie.objects.TrafficLight;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.trigger.GpioTriggerBase;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

/**
 * Trigger for when car is detected
 */
public class CarDetectedTrigger extends GpioTriggerBase {
    private static final Logger LOG = Logger.getLogger(CarDetectedTrigger.class);
    private TrafficLight trafficLight;

    public CarDetectedTrigger(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }

    @Override
    public void invoke(GpioPin gpioPin, PinState pinState) {
        Callable<Void> callback = new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                switch (trafficLight.getState()) {
                    case RED:
                        trafficLight.goGreen();
                        break;
                    case IN_BETWEEN:
                        Thread.sleep(1000);
                        call();
                        break;
                    case MALFUNCTION:
                        trafficLight.goRed();
                        break;
                    case UNINITIALIZED:
                        LOG.error("Traffic light is not initialized yet! please run trafficlight.initialize(); ");
                        break;
                }
                return null;
            }
        };

        try {
            callback.call();
        } catch (Exception e) {
            LOG.error("Unable to call CarDetected Callable");
        }
    }
}
