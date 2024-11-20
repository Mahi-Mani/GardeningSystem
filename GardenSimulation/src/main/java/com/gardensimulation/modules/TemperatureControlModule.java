package com.gardensimulation.modules;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;

public class TemperatureControlModule {

    public void run(Garden garden, int temperature) {
        for (Plant plant: garden.getPlants().values()) {
            if(plant.getHealth() > 0) {
                if (temperature < 50 || temperature > 95) {
                    // Can't do anything.
                } else if (temperature < 60) {
                    // heat up to 75
                    plant.adjustHealth(2);
                } else if (temperature > 80) {
                    // cool up to 75
                    plant.adjustHealth(2);
                }
            }
        }
    }
}
