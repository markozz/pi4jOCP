package com.mark.jaumie.objects;

import com.mark.jaumie.annotations.Device;
import com.pi4j.io.gpio.GpioController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MockedLedTest {

    private LED led1;

    @Before
    public void startUp() {
        GpioController controller = Mockito.mock(GpioController.class);
        led1 = new LED(controller);
    }

    @After
    public void tearDown() {
        led1 = null;
    }

    @Test
    public void testTurnLedOn() {
        led1.turnOn();
        Assert.assertEquals("Expected light to be on",Device.State.ON,led1.getState());
    }

    @Test
    public void testTurnLedOff() {
        led1.turnOff();
        Assert.assertEquals("Expected light to be on",Device.State.OFF,led1.getState());
    }

    @Test
    public void testPrettyName() {
        String name = led1.getPrettyName();
        Assert.assertEquals("Expected name to be LED #1 but was: "+name,"LED #1",name);
    }

    @Test
    public void testState() {
        Device.State state = led1.getState();
        System.out.println(state);
    }
}
