package com.gardensimulation.modules;

import java.util.List;
import java.util.Random;
import java.util.Set;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;

public class PestModule extends GardenModule {

    private static final int BASE_HEALTH_DAMAGE = 8; // Base health damage per pest
    private static final int ADDITIONAL_HEALTH_DAMAGE = 5; // Extra damage per additional pest
    private static final int PESTICIDE_HEALTH_IMPROVMENT = 5;
    private static final int PESTICIDE_EFFECTIVENESS = 80;
    Random random = new Random();

    public PestModule(Garden garden){
        super(garden);
    }

    public void addPests(List<String> pests) {
        for (Plant plant: garden.getPlants().values()) {
            if(plant.isAlive()) {
                Set<String> affectedBy = plant.getCurrentPests();
                for (String parasite : affectedBy) {
                    plant.addPest(parasite);
                }
                reduceHealthForPestAttack(plant);
            }
        }       
    }

    public void run() {
        for (Plant plant: garden.getPlants().values()) {
            if(plant.isAlive()) {
                addPesticides(plant);
            }
        }
    }

    private void addPesticides(Plant plant) {
        for (String pest: plant.getCurrentPests()) {
            int randomNumber = random.nextInt(100) + 1;
            if(randomNumber < PESTICIDE_EFFECTIVENESS) {
                plant.removePest(pest);
                plant.adjustHealth(PESTICIDE_HEALTH_IMPROVMENT);
            }
        }
    }

    /**
     * @param plant The plant being attacked.
     */
    private void reduceHealthForPestAttack(Plant plant) {
        int numberOfPests = plant.getCurrentPests().size();
        int totalHealthDamage = BASE_HEALTH_DAMAGE + (numberOfPests - 1) * ADDITIONAL_HEALTH_DAMAGE;
        plant.adjustHealth(-totalHealthDamage);
    }
}
