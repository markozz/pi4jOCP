package com.mark.jaumie.objects;

import com.mark.jaumie.annotations.Device;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.PinState;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

public class MockedLedTest {

    @Mock
    private GpioController controller;
    private LED led1;

    @Before
    public void startUp() {
        MockitoAnnotations.initMocks(this);
        led1 = new LED(controller);
    }

    @After
    public void tearDown() {
        led1 = null;
    }

    @Test
    public void testTurnLedOn() {
        led1.turnOn();
        Mockito.when(controller.getState(led1.led)).thenReturn(PinState.HIGH);
        Device.State actualState = null;
        try {
            actualState = led1.getState();
        } catch (IOException e) {
            Assert.fail("Expected light to be on but got an IOException");
        }
        Assert.assertEquals("Expected light to be on",Device.State.ON,actualState);
    }

    @Test
    public void testTurnLedOff() {
        led1.turnOff();
        Mockito.when(controller.getState(led1.led)).thenReturn(PinState.LOW);
        Device.State actualState = null;
        try {
            actualState = led1.getState();
        } catch (IOException e) {
            Assert.fail("Expected light to be off but got an IOException");
        }
        Assert.assertEquals("Expected light to be off",Device.State.OFF,actualState);
    }

    @Test
    public void testPrettyName() {
        String name = led1.getName();
        Assert.assertEquals("Expected name to be LED #1 but was: "+name,"LED #1",name);
    }

    @Test(expected = IOException.class)
    public void testStateFalse() throws IOException {
        Device.State state = led1.getState();
    }
}
