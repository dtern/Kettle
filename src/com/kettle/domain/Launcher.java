package com.kettle.domain;

public class Launcher {


    public static void main(String[] args) {

        Kettle kettle = new Kettle("Solo", 55.0, 2.0, 1.4, false);
        kettle.start();

    }
}
