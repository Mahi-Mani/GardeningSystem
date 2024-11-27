package com.gardensimulation;

import com.gardensimulation.Plant.Plants;

import java.util.logging.*;

import java.util.ArrayList;

public class SprinklerController implements Runnable {
    private static final Logger log = Logger.getLogger(SprinklerController.class.getName());
    private TemperatureController temperatureController;
    private boolean isRunning = true;

    public void activateSprinklers(ArrayList<Plants> plants) {
        int avgWaterReq = calculateWaterForGarden(plants);
        int avgWaterLevel = calculateCurrentWaterLevel(plants);
        log.info("Water requirement: " + avgWaterReq);
        log.info("Current water level: " + avgWaterLevel);

        if(plants.size() > 0) {
            if (avgWaterLevel < (avgWaterReq)) {
                for (Plants plant : plants) {
                    plant.waterThePlant(avgWaterReq);
                }
                log.info("Sprinklers activated! Plants received an average of " + avgWaterReq + " units.");
                TemperatureController.setCurrentTemperature(TemperatureController.getCurrentTemperature() - 5);
            } else {
                log.info("Sufficient water in the garden already! No need sprinklers!");
            }
        } else {
            log.info("There are no plants in the garden!");
        }
    }


    private int calculateWaterForGarden(ArrayList<Plants> plants) {
        int total = 0;
        int average = 0;
        for (Plants plant : plants) {
            total = total + plant.getWater_requirement();
        }
        try {
            average = total / plants.size();
        } catch(ArithmeticException e) {
            log.info("There are no plants in the garden!");
        }
        return average;
    }

    private int calculateCurrentWaterLevel(ArrayList<Plants> plants) {
        int total = 0;
        int average = 0;
        for (Plants plant : plants) {
            total = total + plant.getWater_level();
        }
        try {
            average = total / plants.size();
        } catch(ArithmeticException e) {
            log.info("There are no plants in the garden!");
        }
        return average;
    }

    public void reduceWaterLevel() {
        int reduceAmt = 0;
        int temperature = TemperatureController.getCurrentTemperature();

        if(temperature > 100) {
            reduceAmt = 10;
        } else if(temperature > 90) {
            reduceAmt = 7;
        } else if(temperature > 80) {
            reduceAmt = 5;
        } else {
            reduceAmt = 1;
        }

        for(Plants plant: Plants.plantsList) {
            plant.dryThePlant(reduceAmt);
        }
    }

    @Override
    public void run() {
        log.info("Sprinkler Controller thread is running!");
        while(isRunning) {
            this.activateSprinklers(Plants.plantsList);
            try {
//                Periodically check every 10 seconds
                Thread.sleep(10000);
            } catch(InterruptedException ie) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
