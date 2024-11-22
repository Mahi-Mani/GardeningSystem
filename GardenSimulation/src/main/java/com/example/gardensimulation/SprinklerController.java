package com.example.gardensimulation;

import com.example.gardensimulation.Plant.Plants;

import java.util.ArrayList;

public class SprinklerController {
    public void activateSprinklers(ArrayList<Plants> plants) {
        int avgWaterReq = calculateWaterForGarden(plants);
        for(Plants plant: plants) {
            plant.waterThePlant(avgWaterReq);
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
}
