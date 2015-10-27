package com.mark.jaumie;

/**
 * Created by mark on 19-10-15.
 */
public @interface Device {
    public boolean state() default false;
    public String name();
}
