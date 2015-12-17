package com.mark.jaumie.annotations;

/**
 * Created by mark on 19-10-15.
 */
public @interface Device {

    public enum State {
        ON, OFF
    }

    public State state() default State.OFF;
    public String prettyName() default "NOT SET";
    public int id();
}
