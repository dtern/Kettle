package com.kettle.domain;

import java.util.ArrayList;

public class Kettle {

    private final static double ROOM_TEMPERATURE = 18.0;

    private String kettleModel;
    private double currentWaterTemperature;
    private double maxWaterVolume;
    private double currentWaterVolume;
    private boolean switchStatus;


    /* Constructor's */

    public Kettle(){

        kettleModel = "Default";
        currentWaterTemperature = 18.0;
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

    public static void main(String[] args) {

        Kettle kettle = new Kettle("Solo", 55.0, 2.0, 1.4, false);
        System.out.println("Тест");
        kettle.launcher(kettle);


    }


    public void launcher(Kettle k){

        Environment environment = new Environment();
        environment.addKettle(k);
        Thread t = new Thread(environment);
        t.start();

    }


    /* Methods */

    public void switchKettleOn(){
        switchStatus = true;
    }


    public void switchKettleOff(){
        switchStatus = false;
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
        if (currentWaterTemperature - amountDegree < ROOM_TEMPERATURE) {
            currentWaterTemperature = ROOM_TEMPERATURE;
        }
        else {
            currentWaterTemperature = currentWaterTemperature - amountDegree;
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

}