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

        // Fields setup
        double reduceTemp = 4.0;
        double currentWaterTemp = 34.0;
        double roomTemp = 18.0;
        double deltaTemp = currentWaterTemp - roomTemp;

        kettle.setCurrentWaterTemperature(currentWaterTemp);
        kettle.setRoomTemperature(roomTemp);
        kettle.reduceTemperature(reduceTemp);

        // Test

        if (reduceTemp > deltaTemp) {
            assertTrue("Not minimal temperature! Expected: 18.0, Real is: "  + kettle.getCurrentTemperature(), kettle.getCurrentTemperature() == 18.0);
        }
        else {
            assertTrue("Abnormal temperature! Expected: " + (currentWaterTemp - reduceTemp) + ", Real is:  " + kettle.getCurrentTemperature(), kettle.getCurrentTemperature() == 30);
        }
    }


    @Test
    public void switchKettleOff() throws Exception {
        if (kettle.getSwitchStatus() == true) {
            kettle.switchKettleOff();
            assertFalse("Kettle don't swich off", kettle.getSwitchStatus());
        }
    }


    @Test
    public void switchKettleOn() throws Exception {
        kettle.switchKettleOn();
        assertTrue("Kettle don't swich on", kettle.getSwitchStatus());
    }


    @Test (expected = Kettle.FalseStateException.class)
    public void FalseKettleTurnOff() throws Exception {
        kettle.switchKettleOff();
        kettle.switchKettleOff();
    }


    @Test  (expected = Kettle.FalseStateException.class)
    public void FalseKettleTurnOn() throws Exception {
        kettle.switchKettleOn();
        kettle.switchKettleOn();
    }

    @Test
    public void getKettleModel() throws Exception {
        assertEquals("Errore default model name", "Default", kettle.getKettleModel());
}

}


