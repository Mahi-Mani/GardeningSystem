package com.example.gardensimulation;

import com.example.gardensimulation.Plant.Plants;
import java.util.logging.*;

import java.util.ArrayList;

public class SprinklerController {
    private static final Logger log = Logger.getLogger(SprinklerController.class.getName());

    public void activateSprinklers(ArrayList<Plants> plants) {
        int avgWaterReq = calculateWaterForGarden(plants);
        int avgWaterLevel = calculateCurrentWaterLevel(plants);
        log.info("Water requirement: " + avgWaterReq);
        log.info("Current water level: " + avgWaterLevel);

        if(avgWaterLevel < (avgWaterReq)) {
            for(Plants plant: plants) {
                plant.waterThePlant(avgWaterReq);
            }
            log.info("Sprinklers activated! Plants received an average of " + avgWaterReq + " units.");
        } else {
            log.info("Sufficient water in the garden already! No need sprinklers!");
        }
    }

    private int calculateWaterForGarden(ArrayList<Plants> plants) {
        int total = 0;
        for(Plants plant: plants) {
            total = total + plant.getWater_requirement();
        }
        int average = total / plants.size();
        return average;
    }

    private int calculateCurrentWaterLevel(ArrayList<Plants> plants) {
        int total = 0;
        for(Plants plant: plants) {
            total = total + plant.getWater_level();
        }
        int average = total / plants.size();
        return average;
    }
}
