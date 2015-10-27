package com.mark.jaumie;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LedTest {

    private LED led1;

    @Before
    public void startUp() {
        led1 = new LED();
    }

    @After
    public void tearDown() {
        led1 = null;
    }

    @Test
    public void testTurnOn() {
        led1.turnOn();
        Assert.assertTrue("Expected lights to be on", led1.getState());
    }

    @Test
    public void testTurnOff() {
        led1.turnOff();
        Assert.assertFalse("Expected lights to be off",led1.getState());
    }
}
