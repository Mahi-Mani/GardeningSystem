package com.gardensimulation.modules;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;

public class WateringModule {

    public void run(Garden garden) {
        for(Plant plant: garden.getPlants().values()) {
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
