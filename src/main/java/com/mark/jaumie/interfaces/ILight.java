package com.mark.jaumie.interfaces;

import com.mark.jaumie.annotations.Device;

import java.io.IOException;

public interface ILight {
    public void turnOn();
    public void turnOff();
    public String getName();
    public Device.State getState() throws IOException;
}
