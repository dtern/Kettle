package com.kettle.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KettleTest {

    Kettle kettle = null;


    @Before
    public void setup() throws Exception {
        kettle = new Kettle();
    }



    @Test
    public void reduceTemperature() throws Exception {
        kettle.reduceTemperature(20.0);
        kettle.setCurrentWaterTemperature(12.0);
        assertTrue("Abnormal temperature",kettle.getCurrentTemperature() >= 18.0);

        /*
        Barbarian method ? he-he
        if (kettle.getCurrentTemperature() < 18.0){
            fail("The temperature may not fall below the room");
        }
        */
    }


    @Test
    public void switchKettleOff() throws Exception {
        kettle.switchKettleOff();
        assertFalse("Kettle don't swich off", kettle.getSwitchStatus());

    }


    @Test
    public void switchKettleOn() throws Exception {
        kettle.switchKettleOn();
        assertTrue("Kettle don't swich on", kettle.getSwitchStatus());
    }


    @Test
    public void getKettleModel() throws Exception {
        assertEquals("Errore default model name", "Default", kettle.getKettleModel());
}

}


