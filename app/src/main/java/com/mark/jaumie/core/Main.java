package com.mark.jaumie.core;

import com.pi4j.io.gpio.*;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        LOG.info("------ Starting Jaumie ------");
        Thread jaumie = new Jaumie();
        jaumie.setName("Jaumie");
        jaumie.start();
        try {
            jaumie.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.error("Thread interupted..");
        }

        LOG.info("------ Ending Jaumie ------");
    }
}
