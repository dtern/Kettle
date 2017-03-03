package com.kettle.domain;

import java.util.ArrayList;


public class Kettle {

    private String kettleModel;
    private double currentWaterTemperature;
    private double roomTemperature;
    private double maxWaterVolume;
    private double currentWaterVolume;
    private boolean switchStatus;


    /* Constructor's */

    public Kettle(){

        kettleModel = "Default";
        currentWaterTemperature = 18.0;
        roomTemperature = 18.0;
        maxWaterVolume = 2.0;
        currentWaterVolume = 0.0;
        switchStatus = false;

    }


    public Kettle(String kettleModel, double currentWaterTemperature, double maxWaterVolume, double currentWaterVolume, boolean switchStatus){

        this.kettleModel = kettleModel;
        this.currentWaterTemperature = currentWaterTemperature;
        this.maxWaterVolume = maxWaterVolume;
        this.currentWaterVolume = currentWaterVolume;
        this.switchStatus = switchStatus;

    }


  /* Launcher */

    public void start(){

        Environment environment = new Environment();
        environment.addKettle(this);

        try {
            this.switchKettleOff();
            this.switchKettleOff();
        } catch (FalseStateException e) {
            e.printStackTrace();
            e.printStackTrace();
        }

        Thread t = new Thread(environment);
        t.start();

    }


    /* Methods */

    public void switchKettleOn() throws FalseStateException {
        if (switchStatus == true){
            throw new FalseStateException();
        }
        else {
            switchStatus = true;
        }
    }


    public void switchKettleOff() throws FalseStateException {
        if (switchStatus == false){
            throw new FalseStateException();
        }
        else {
            switchStatus = false;
        }
    }


    public void addWaterIntoKettle(double amountOfWater){
        if (currentWaterVolume + amountOfWater <= maxWaterVolume && !switchStatus){
            currentWaterVolume = currentWaterVolume + amountOfWater;
        }
        else {
            System.out.println("The volume of the kettle can not add this amount of water");
        }
    }


    public void removeWaterFromKettle(double amountOfWater){
        if (currentWaterVolume >= amountOfWater && !switchStatus){
            currentWaterVolume = currentWaterVolume - amountOfWater;
        }
        else {
            System.out.println("The kettle is not enough water");
        }

    }


    public void removeAllWaterFromKettle(){
        if (!switchStatus) {
            currentWaterVolume = 0.0;
        }
    }


    public void FillTheKettleCompletely(){
        if (!switchStatus) {
            currentWaterVolume = maxWaterVolume;
        }
    }

    public double getCurrentVolume(){
        return currentWaterVolume;
    }


    public void reduceTemperature(double amountDegree){
        if (currentWaterTemperature - amountDegree < roomTemperature) {
            currentWaterTemperature = roomTemperature + 2;
        }
        else {
            currentWaterTemperature = currentWaterTemperature - amountDegree + 2;
        }
    }


    public void increaseTemperature(double amountDegree){
        if (currentWaterTemperature + amountDegree >= 100.0){
            currentWaterTemperature = 100.0;
            switchStatus = false;
        }
        else {
            currentWaterTemperature = currentWaterTemperature + amountDegree;
        }
    }


    public double getCurrentTemperature(){
        return currentWaterTemperature;
    }


    public boolean getSwitchStatus() {
        return switchStatus;
    }

    public String getKettleModel() {
        return kettleModel;
    }

    public void setCurrentWaterTemperature(double currentWaterTemperature) {
        this.currentWaterTemperature = currentWaterTemperature;
    }

    public void setCurrentWaterVolume(double currentWaterVolume) {
        this.currentWaterVolume = currentWaterVolume;
    }

    public void setRoomTemperature(double roomTemperature) {
        this.roomTemperature = roomTemperature;
    }



    /* Runnable classes */


    private class Environment implements Runnable {

        private ArrayList<Kettle> kettleList = new ArrayList<>();

        public void addKettle(Kettle kettle){
            kettleList.add(kettle);
        }



        @Override
        public void run() {

            while (true){
                for (Kettle ket : kettleList){
                    ket.reduceTemperature(0.1);
                    System.out.println(ket.getCurrentTemperature());
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    /* Exception classes */

    public class FalseStateException extends Exception {

    }



}