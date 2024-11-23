package com.gardensimulation.modules;

import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gardensimulation.models.Garden;
import com.gardensimulation.models.Plant;

public class PestModule extends GardenModule {
    private static Logger logger = LogManager.getLogger();

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
                for (String parasite : pests) {
                    if(plant.getParasites().contains(parasite)) {
                        plant.addPest(parasite);
                    }
                } 
                if(plant.getCurrentPests().size() > 0 ) {
                    reduceHealthForPestAttack(plant);
                }
            }
        }       
    }

    public void run() {
        logger.info("Running Pest Control automated module");
        for (Plant plant: garden.getPlants().values()) {
            if(plant.isAlive() && plant.getCurrentPests().size() > 0) {
                logger.info("Aww! {} plant at position {} got affected by {} pests", plant.getName(), plant.getPosition(), plant.getCurrentPests().size());
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
                logger.info("Sprayed pesticide for {} and pest was removed. +{} health to the plant", pest, PESTICIDE_HEALTH_IMPROVMENT);
            } else {
                logger.info("Sprayed pesticide for {} and it was not effective. Pest still exists", pest);
            }
        }
    }

    /**
     * @param plant The plant being attacked.
     */
    private void reduceHealthForPestAttack(Plant plant) {
        logger.info("{} Plant at {} position got affected by [{}] pests.", plant.getName(), plant.getPosition(), plant.getCurrentPests());
        int numberOfPests = plant.getCurrentPests().size();
        int totalHealthDamage = BASE_HEALTH_DAMAGE + (numberOfPests - 1) * ADDITIONAL_HEALTH_DAMAGE;
        logger.info("{} Plant at {} position got affected by [{}] pests. -{} to the heath", plant.getName(), plant.getPosition(), plant.getCurrentPests(), totalHealthDamage);
        plant.adjustHealth(-totalHealthDamage);
    }
}
