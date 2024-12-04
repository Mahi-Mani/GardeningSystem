package com.gardensimulation;

import com.almasb.fxgl.core.View;
import com.gardensimulation.Plant.Plants;

import java.util.List;
import java.util.logging.*;

import java.util.ArrayList;

public class SprinklerController implements Runnable {
    private static final Logger log = Logger.getLogger(SprinklerController.class.getName());
    private TemperatureController temperatureController;
    private boolean isRunning = true;
    public static boolean isSprinklerRunning = false;
    public boolean isRaining = false;

    public void activateSprinklers(List<Plants> plants) {
        int avgWaterReq = calculateWaterForGarden(plants);
        int avgWaterLevel = calculateCurrentWaterLevel(plants);
        log.info("Water requirement: " + avgWaterReq);
        log.info("Current water level: " + avgWaterLevel);

        if (plants.size() > 0 && !isRaining) {
            if (avgWaterLevel < (avgWaterReq)) {
                for (Plants plant : plants) {
                    plant.waterThePlant(avgWaterReq);
                }
                log.info("Sprinklers activated! Plants received an average of " + avgWaterReq + " units.");
                ViewController.addLogMessage("Plants received water: " + avgWaterReq + " units.!", "info");
                ViewController.appendLogToFile("Plants received water: " + avgWaterReq + " units.!", "info");
                isSprinklerRunning = true;
                TemperatureController.setCurrentTemperature(TemperatureController.getCurrentTemperature() - 5);
//                ViewController.updateSprinklerUI("ON", "https://media1.giphy.com/media/7Xp6WZXFADXkkP7z9X/giphy.gif?cid=6c09b9529330fin1czs6t2w4xu0tkphyk6eibycol4nbqegs&ep=v1_internal_gif_by_id&rid=giphy.gif&ct=g");
            } else {
                log.info("Sufficient water in the garden already! No need sprinklers!");
                ViewController.addLogMessage("Sufficient water in the garden already! No need sprinklers!", "info");
                ViewController.appendLogToFile("Sufficient water in the garden already! No need sprinklers!", "info");
                isSprinklerRunning = false;
//                ViewController.updateSprinklerUI("OFF", "https://www.groundsguys.com/us/en-us/grounds-guys/_assets/expert-tips/sprinkler-system.webp");
            }
        } else {
            log.info("There are no plants in the garden!");
            ViewController.appendLogToFile("There are no plants in the garden!", "info");
            isSprinklerRunning = false;
        }
    }


    private int calculateWaterForGarden(List<Plants> plants) {
        int total = 0;
        int average = 0;
        for (Plants plant : plants) {
            total = total + plant.getWater_requirement();
        }
        try {
            average = total / plants.size();
        } catch (ArithmeticException e) {
            log.info("There are no plants in the garden!");
        }
        return average;
    }

    private int calculateCurrentWaterLevel(List<Plants> plants) {
        int total = 0;
        int average = 0;
        for (Plants plant : plants) {
            total = total + plant.getWater_level();
        }
        try {
            average = total / plants.size();
        } catch (ArithmeticException e) {
            log.info("There are no plants in the garden!");
        }
        return average;
    }

    public void reduceWaterLevel() {
        int reduceAmt = 0;
        int temperature = TemperatureController.getCurrentTemperature();

        if (temperature > 100) {
            reduceAmt = 10;
        } else if (temperature > 90) {
            reduceAmt = 7;
        } else if (temperature > 80) {
            reduceAmt = 5;
        } else {
            reduceAmt = 1;
        }

        for (Plants plant : Plants.plantsList) {
            plant.dryThePlant(reduceAmt);
        }
    }

    @Override
    public void run() {
        log.info("Sprinkler Controller thread is running!");
        while (isRunning) {
            this.activateSprinklers((ArrayList<Plants>) Plants.plantsList);
            try {
//                Periodically check every 10 seconds
                Thread.sleep(10000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                isRunning = false;
            }
        }
    }
}
