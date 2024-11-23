package com.gardensimulation.modules;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;

public class TemperatureModule extends GardenModule {

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
        float newTemperature = 0;
        if(currentTemperature < IDEAL_TEMPERATURE) {
            newTemperature = Math.min(currentTemperature + TEMPERATURE_CONTROL_RATE, IDEAL_TEMPERATURE);
        } else {
            newTemperature = Math.max(currentTemperature - TEMPERATURE_CONTROL_RATE, IDEAL_TEMPERATURE);
        }
        setCurrentTemperature(newTemperature);
        adjustPlantHealth();
    }

    private void adjustPlantHealth() {
        for (Plant plant : garden.getPlants().values()) {
            if (plant.isAlive()) {
                float deviation = Math.abs(currentTemperature - IDEAL_TEMPERATURE);

                if (deviation <= IDEAL_RANGE) {
                    plant.adjustHealth(5);
                } else if (deviation <= MODERATE_RANGE) {
                    plant.adjustHealth(-5);
                } else {
                    plant.adjustHealth(-10);
                }
            }
        }
    }
}