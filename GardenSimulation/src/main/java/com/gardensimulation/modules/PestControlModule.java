package com.gardensimulation.modules;

import java.util.Set;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Parasites;
import com.gardensimulation.models.Plant;

public class PestControlModule {
    
    public void run(Garden garden) {
        for (Plant plant: garden.getPlants().values()) {
            if(plant.getHealth() > 0) {
                Set<Parasites> affectedBy = plant.getCurrentPests();
                for (Parasites parasite : affectedBy) {
                    plant.handlePestAttack(parasite);
                }
            }
        }
    }
}
