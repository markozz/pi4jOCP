package com.mark.jaumie.objects;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class MockedTrafficLightTest {

    @Mock
    private GpioController gpio;
    @Mock
    private GpioPinDigitalOutput red;
    @Mock
    private GpioPinDigitalOutput yellow;
    @Mock
    private GpioPinDigitalOutput green;

    private TrafficLight trafficLight;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"Red", PinState.LOW)).thenReturn(red);
        Mockito.when(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02,"Yellow", PinState.LOW)).thenReturn(yellow);
        Mockito.when(gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03,"Green", PinState.LOW)).thenReturn(green);

        trafficLight = new TrafficLight(gpio,RaspiPin.GPIO_01,RaspiPin.GPIO_02,RaspiPin.GPIO_03);
    }

    @After
    public void tearDown() {

    }

    @Test
    public void testBootupState() {
        // Expect the trafficlight to be on red once system is booted up
        Assert.assertTrue(TrafficLightState.UNINITIALIZED == trafficLight.getState());

        trafficLight.initialize();
        Assert.assertTrue(TrafficLightState.RED == trafficLight.getState());
    }

    @Test
    public void testGoGreen() {
        // Only greenlight should be on

        // Should be on for 5 seconds
    }

    @Test
    public void testGoRed() throws InterruptedException {
        // Only red should be on
        trafficLight.goMalfunction();
        Assert.assertTrue("Expected MALFUNCTION but was: "+trafficLight.getState(), TrafficLightState.MALFUNCTION == trafficLight.getState());
        trafficLight.goRed();
        Thread.sleep(1200);
        Assert.assertTrue("Expected RED but was: "+trafficLight.getState(), TrafficLightState.RED == trafficLight.getState());
    }

    @Test
    public void testFromGreenToRed() {
        // Trafficlight should go from green, to yellow, to red
    }

    @Test
    public void testFromRedToGreen() throws InterruptedException {
        // Trafficlight should go from red, to yellow, to green
        trafficLight.goGreen();
        Thread.sleep(200);
        Assert.assertTrue("Expected IN_BETWEEN but was: "+trafficLight.getState() ,TrafficLightState.IN_BETWEEN == trafficLight.getState());
        Thread.sleep(1200);
        Assert.assertTrue("Expected GREEN but was: "+trafficLight.getState() ,TrafficLightState.GREEN == trafficLight.getState());
        Thread.sleep(5200);
        Assert.assertTrue("Expected IN_BETWEEN but was: "+trafficLight.getState() ,TrafficLightState.IN_BETWEEN == trafficLight.getState());
        Thread.sleep(1200);
        Assert.assertTrue("Expected RED but was: "+trafficLight.getState(), TrafficLightState.RED == trafficLight.getState());

    }

    @Test
    public void testFailureScenario() {
        // If no light is displayed or stuck it should go into failure mode (blink yellow)
        Assert.assertTrue("Expected UNINITIALIZED but was: "+trafficLight.getState(),TrafficLightState.UNINITIALIZED == trafficLight.getState());
        trafficLight.goMalfunction();
        Assert.assertTrue("Expected MALFUNCTION but was: "+trafficLight.getState(),TrafficLightState.MALFUNCTION == trafficLight.getState());
    }
}
