package com.mark.jaumie.objects;


import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListener;
import com.pi4j.io.gpio.trigger.GpioTrigger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class MockedButtonTest {

    private GpioController controller = null;
    private Button button1 = null;

    @Before
    public void initiateClasses() {
        controller = Mockito.mock(GpioController.class);
    }

    @Test
    public void testButtonPressed() {
        GpioPinDigitalInput mockedPin = createMockedPressedButton();
        Mockito.when(controller.provisionDigitalInputPin(RaspiPin.GPIO_01,"Button #1")).thenReturn(mockedPin);
        button1 = new Button(controller);

        Assert.assertTrue("Expected button to be pressed",button1.isPressed());
    }

    @Test
    public void testButtonUnpressed() {
        GpioPinDigitalInput mockedPin = createMockedUnpressedButton();
        Mockito.when(controller.provisionDigitalInputPin(RaspiPin.GPIO_01,"Button #1")).thenReturn(mockedPin);
        button1 = new Button(controller);

        Assert.assertFalse("Expected unpressed button",button1.isPressed());
    }

    private GpioPinDigitalInput createMockedUnpressedButton() {
        return new GpioPinDigitalInput() {
            public boolean hasDebounce(PinState pinState) {
                return false;
            }

            public int getDebounce(PinState pinState) {
                return 0;
            }

            public void setDebounce(int i) {

            }

            public void setDebounce(int i, PinState... pinStates) {

            }

            public boolean isHigh() {
                return false;
            }

            public boolean isLow() {
                return false;
            }

            public PinState getState() {
                return PinState.LOW;
            }

            public boolean isState(PinState pinState) {
                return false;
            }

            public Collection<GpioTrigger> getTriggers() {
                return null;
            }

            public void addTrigger(GpioTrigger... gpioTriggers) {

            }

            public void addTrigger(List<? extends GpioTrigger> list) {

            }

            public void removeTrigger(GpioTrigger... gpioTriggers) {

            }

            public void removeTrigger(List<? extends GpioTrigger> list) {

            }

            public void removeAllTriggers() {

            }

            public GpioProvider getProvider() {
                return null;
            }

            public Pin getPin() {
                return null;
            }

            public void setName(String s) {

            }

            public String getName() {
                return null;
            }

            public void setTag(Object o) {

            }

            public Object getTag() {
                return null;
            }

            public void setProperty(String s, String s1) {

            }

            public boolean hasProperty(String s) {
                return false;
            }

            public String getProperty(String s) {
                return null;
            }

            public String getProperty(String s, String s1) {
                return null;
            }

            public Map<String, String> getProperties() {
                return null;
            }

            public void removeProperty(String s) {

            }

            public void clearProperties() {

            }

            public void export(PinMode pinMode) {

            }

            public void export(PinMode pinMode, PinState pinState) {

            }

            public void unexport() {

            }

            public boolean isExported() {
                return false;
            }

            public void setMode(PinMode pinMode) {

            }

            public PinMode getMode() {
                return null;
            }

            public boolean isMode(PinMode pinMode) {
                return false;
            }

            public void setPullResistance(PinPullResistance pinPullResistance) {

            }

            public PinPullResistance getPullResistance() {
                return null;
            }

            public boolean isPullResistance(PinPullResistance pinPullResistance) {
                return false;
            }

            public Collection<GpioPinListener> getListeners() {
                return null;
            }

            public void addListener(GpioPinListener... gpioPinListeners) {

            }

            public void addListener(List<? extends GpioPinListener> list) {

            }

            public boolean hasListener(GpioPinListener... gpioPinListeners) {
                return false;
            }

            public void removeListener(GpioPinListener... gpioPinListeners) {

            }

            public void removeListener(List<? extends GpioPinListener> list) {

            }

            public void removeAllListeners() {

            }

            public GpioPinShutdown getShutdownOptions() {
                return null;
            }

            public void setShutdownOptions(GpioPinShutdown gpioPinShutdown) {

            }

            public void setShutdownOptions(Boolean aBoolean) {

            }

            public void setShutdownOptions(Boolean aBoolean, PinState pinState) {

            }

            public void setShutdownOptions(Boolean aBoolean, PinState pinState, PinPullResistance pinPullResistance) {

            }

            public void setShutdownOptions(Boolean aBoolean, PinState pinState, PinPullResistance pinPullResistance, PinMode pinMode) {

            }
        };
    }

    private GpioPinDigitalInput createMockedPressedButton() {
        return new GpioPinDigitalInput() {
            public boolean hasDebounce(PinState pinState) {
                return false;
            }

            public int getDebounce(PinState pinState) {
                return 0;
            }

            public void setDebounce(int i) {

            }

            public void setDebounce(int i, PinState... pinStates) {

            }

            public boolean isHigh() {
                return false;
            }

            public boolean isLow() {
                return false;
            }

            public PinState getState() {
                return PinState.HIGH;
            }

            public boolean isState(PinState pinState) {
                return false;
            }

            public Collection<GpioTrigger> getTriggers() {
                return null;
            }

            public void addTrigger(GpioTrigger... gpioTriggers) {

            }

            public void addTrigger(List<? extends GpioTrigger> list) {

            }

            public void removeTrigger(GpioTrigger... gpioTriggers) {

            }

            public void removeTrigger(List<? extends GpioTrigger> list) {

            }

            public void removeAllTriggers() {

            }

            public GpioProvider getProvider() {
                return null;
            }

            public Pin getPin() {
                return null;
            }

            public void setName(String s) {

            }

            public String getName() {
                return null;
            }

            public void setTag(Object o) {

            }

            public Object getTag() {
                return null;
            }

            public void setProperty(String s, String s1) {

            }

            public boolean hasProperty(String s) {
                return false;
            }

            public String getProperty(String s) {
                return null;
            }

            public String getProperty(String s, String s1) {
                return null;
            }

            public Map<String, String> getProperties() {
                return null;
            }

            public void removeProperty(String s) {

            }

            public void clearProperties() {

            }

            public void export(PinMode pinMode) {

            }

            public void export(PinMode pinMode, PinState pinState) {

            }

            public void unexport() {

            }

            public boolean isExported() {
                return false;
            }

            public void setMode(PinMode pinMode) {

            }

            public PinMode getMode() {
                return null;
            }

            public boolean isMode(PinMode pinMode) {
                return false;
            }

            public void setPullResistance(PinPullResistance pinPullResistance) {

            }

            public PinPullResistance getPullResistance() {
                return null;
            }

            public boolean isPullResistance(PinPullResistance pinPullResistance) {
                return false;
            }

            public Collection<GpioPinListener> getListeners() {
                return null;
            }

            public void addListener(GpioPinListener... gpioPinListeners) {

            }

            public void addListener(List<? extends GpioPinListener> list) {

            }

            public boolean hasListener(GpioPinListener... gpioPinListeners) {
                return false;
            }

            public void removeListener(GpioPinListener... gpioPinListeners) {

            }

            public void removeListener(List<? extends GpioPinListener> list) {

            }

            public void removeAllListeners() {

            }

            public GpioPinShutdown getShutdownOptions() {
                return null;
            }

            public void setShutdownOptions(GpioPinShutdown gpioPinShutdown) {

            }

            public void setShutdownOptions(Boolean aBoolean) {

            }

            public void setShutdownOptions(Boolean aBoolean, PinState pinState) {

            }

            public void setShutdownOptions(Boolean aBoolean, PinState pinState, PinPullResistance pinPullResistance) {

            }

            public void setShutdownOptions(Boolean aBoolean, PinState pinState, PinPullResistance pinPullResistance, PinMode pinMode) {

            }
        };
    }
}
