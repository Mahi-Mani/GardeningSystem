package com.gardensimulation.modules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;

public class TemperatureModule extends GardenModule {
    private static Logger logger = LogManager.getLogger();
    private float currentTemperature;
    private static final float IDEAL_TEMPERATURE = 75;
    private static final float TEMPERATURE_CONTROL_RATE  = 30;

    // Define ranges for health adjustments
    private static final float IDEAL_RANGE = 10; // ±10°F is ideal
    private static final float MODERATE_RANGE = 20; // ±20°F is moderate stress

    public TemperatureModule(Garden garden){
        super(garden);
        currentTemperature = IDEAL_TEMPERATURE;
    }

    public float getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(float temp) {
         currentTemperature = temp;
    }

    public void run() {
        logger.info("Running Temperature automated module");
        float newTemperature = currentTemperature;
        if(currentTemperature < IDEAL_TEMPERATURE) {
            newTemperature = Math.min(currentTemperature + TEMPERATURE_CONTROL_RATE, IDEAL_TEMPERATURE);
            logger.info("Current temperature is {} F.", currentTemperature);
            logger.info("Heating up the eniroment to {} F.", newTemperature);
        } else if (currentTemperature < IDEAL_TEMPERATURE) {
            newTemperature = Math.max(currentTemperature - TEMPERATURE_CONTROL_RATE, IDEAL_TEMPERATURE);
            logger.info("Current temperature is {} F.", currentTemperature);
            logger.info("Cooling up the eniroment to {} F.", newTemperature);
        } else {
            logger.info("Current temperature is at ideal temperature. {} F.", currentTemperature);
        }
        setCurrentTemperature(newTemperature);
        adjustPlantHealth();
    }

    private void adjustPlantHealth() {
        float deviation = Math.abs(currentTemperature - IDEAL_TEMPERATURE);
        int healthUpdate = 0;
        if (deviation <= IDEAL_RANGE) {
            logger.info("After adjusting, temperature is in ideal range! +5 to all the plants health.");
            healthUpdate = 5;
        } else if (deviation <= MODERATE_RANGE) {
            logger.info("After adjusting, temperature is in moderate bad range! -5 to the plants health.");
            healthUpdate = -5;
        } else {
            logger.info("After adjusting, temperature is in bad range! -10 to the plants health.");
            healthUpdate = -10;
        }

        for (Plant plant : garden.getPlants().values()) {
            if (plant.isAlive()) {
                plant.adjustHealth(healthUpdate);
            }
        }
    }
}