package com.gardensimulation.modules;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;

public class WateringModule extends GardenModule {

    public WateringModule(Garden garden){
        super(garden);
    }

    public void addRain(int rain) {
        for(Plant plant: garden.getPlants().values()) {
            if(plant.isAlive()) {
                plant.adjustWaterLevel(rain);
            }
        }
    }

    public void run() {
        for(Plant plant: garden.getPlants().values()) {
            // Water drunk by plant today.
            plant.adjustWaterLevel(-1);
            
            int currentWaterLevel = plant.getCurrentWaterLevel();
            int requiredWater = plant.getWaterRequirement();
            int waterDifference = currentWaterLevel - requiredWater;

            if (waterDifference >= -3 && waterDifference <= 3) {
                plant.adjustHealth(5); // Optimal water level bonus
            } else if (waterDifference > 1.5 * waterDifference) {
                plant.adjustHealth(-5);
            }

            plant.adjustWaterLevel(waterDifference);
        }
    }
}
