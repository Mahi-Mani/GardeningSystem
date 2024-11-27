package com.gardensimulation.modules;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WateringModule extends GardenModule {

    private static Logger logger = LogManager.getLogger();

    public WateringModule(Garden garden){
        super(garden);
    }

    public void addRain(int rain) {
        logger.info("Woow! Its raining. {} levels if water to all plants", rain);
        for(Plant plant: garden.getPlants().values()) {
            if(plant.isAlive()) {
                plant.adjustWaterLevel(rain);
            }
        }
    }

    public void run() {
        logger.info("Running water adjustment module");
        for(Plant plant: garden.getPlants().values()) {
            // Water drunk by plant today.
            plant.adjustWaterLevel(-1);

            int currentWaterLevel = plant.getCurrentWaterLevel();
            int requiredWater = plant.getWaterRequirement();
            int waterDifference = currentWaterLevel - requiredWater;

            if (waterDifference >= -3 && waterDifference <= 3) {
                logger.info("Current water level for {} plant at position {} is around desired water level - {} .health is increased by +5", plant.getName(), plant.getPosition(), plant.getCurrentWaterLevel());
                plant.adjustHealth(5); // Optimal water level bonus
            } else if (waterDifference > 1.5 * waterDifference) {
                logger.info("Current water level for {} plant at position {} is very high than optimal level. health is decreased by -5", plant.getName(), plant.getPosition(), plant.getCurrentWaterLevel());
                plant.adjustHealth(-5);
            } else if (waterDifference < -3) {
                logger.info("Current water level for {} plant at position {} is at low water level - {} .health is decreased by -5", plant.getName(), plant.getPosition(), plant.getCurrentWaterLevel());
            } else {
                logger.info("Current water level for {} plant at position {} is {}", plant.getName(), plant.getPosition(), plant.getCurrentWaterLevel());               
            }

            if(waterDifference < 0) {
                logger.info("Adjusting water level of {} plant at position {} to required water level - {}", plant.getName(), plant.getPosition(), requiredWater);               
                plant.adjustWaterLevel(waterDifference);
            }
        }
    }
}
