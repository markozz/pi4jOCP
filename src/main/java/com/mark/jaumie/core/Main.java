package com.mark.jaumie.core;

public class Main {

    public static void main(String[] args) {
        System.out.println("------ Starting Jaumie ------");
        Thread jaumie = new Jaumie();
        jaumie.setName("Jaumie");
        jaumie.start();
    }
}
