package com.kettle.domain;

import org.junit.Test;
import static org.junit.Assert.*;

public class KettleTest {

    @Test
    public void reduceTemperature() throws Exception {
        Kettle kettle = new Kettle();
        kettle.reduceTemperature(20.0);
        kettle.setCurrentWaterTemperature(12.0);
        if (kettle.getCurrentTemperature() < 18.0){
            fail("The temperature may not fall below the room");
        }
    }


    @Test
    public void switchKettleOff() throws Exception {
        Kettle kettle = new Kettle();
        kettle.switchKettleOff();
        if (kettle.getSwitchStatus() != false){
            fail("Kettle don't swich off");
        }
    }


    @Test
    public void switchKettleOn() throws Exception {
        Kettle kettle = new Kettle();
        kettle.switchKettleOn();
        if (kettle.getSwitchStatus() != true){
            fail("Kettle don't swich on");
        }
    }


    @Test
    public void getKettleModel() throws Exception {
        Kettle kettle = new Kettle();
        assertEquals("Errore default model name", "Default", kettle.getKettleModel());
}

}


