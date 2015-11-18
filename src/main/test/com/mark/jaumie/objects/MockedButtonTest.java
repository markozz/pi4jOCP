package com.mark.jaumie.objects;


import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MockedButtonTest {

    @Mock
    private GpioController controller;
    @Mock
    private GpioPinDigitalInput mockedPin;
    private Button button1 = null;

    @Before
    public void initiateClasses() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testButtonPressed() {
        Mockito.when(controller.provisionDigitalInputPin(RaspiPin.GPIO_01,"Button #1")).thenReturn(mockedPin);
        Mockito.when(mockedPin.getState()).thenReturn(PinState.HIGH);
        button1 = new Button(controller);

        Assert.assertTrue("Expected button to be pressed",button1.isPressed());
    }

    @Test
    public void testButtonUnpressed() {
        Mockito.when(controller.provisionDigitalInputPin(RaspiPin.GPIO_01,"Button #1")).thenReturn(mockedPin);
        Mockito.when(mockedPin.getState()).thenReturn(PinState.LOW);
        button1 = new Button(controller);

        Assert.assertFalse("Expected unpressed button",button1.isPressed());
    }
}
