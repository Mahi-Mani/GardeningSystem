package com.gardensimulation.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Plant {
    private static Logger logger = LogManager.getLogger();
    private String name;
    private int position;
    private int waterRequirement;
    private Set<String> parasites; // List of pests the plant is vulnerable to
    private Set<String> currentPests; // Active pests currently affecting the plant
    private float health;
    private int currentWaterLevel; // Tracks how much water the plant has

    public Plant(String name, int waterRequirement, Set<String> parasites, int position) {
        this.name = name;
        this.waterRequirement = waterRequirement;
        this.parasites = parasites;
        this.currentPests = new HashSet<String>();
        this.health = 100; // Default to fully healthy
        this.currentWaterLevel = 0;
        this.position = position;
    }

    /**
     * @param amount
     */
    public synchronized void adjustWaterLevel(int amount) {
        currentWaterLevel += amount;
    }

    /**
     * Centralized health adjustment method.
     * 
     * @param healthChange 
     */
    public synchronized void adjustHealth(int healthChange) {
        this.health = Math.max(0, Math.min(100, this.health+healthChange));
        if (health == 0) {
            logger.info("Sad :(! {} plant at positon {} died", getName(), position);
        }
    }

    /**
     * Adds Pest.
     */
    public synchronized void addPest(String pest) {
        if(isAlive()) {
            currentPests.add(pest);
        }
    }

    /**
     * Removes a pest during pest control and adjusts health.
     */
    public synchronized void removePest(String pest) {
        if (currentPests.remove(pest)) {
            adjustHealth(+10); // Restores health upon pest removal
        }
    }

    public synchronized void adjustHealthForSuddenTemperatureChange(float temperature) {
        if (getHealth() != 0) {
            int changeHealth = 0;
            if (temperature < 50 || temperature > 95) {
                changeHealth =  -10; // Severe penalty
            } else if (temperature < 60 || temperature > 85) {
                changeHealth =  -5; // Mild penalty
            } else {
                changeHealth =  3; // Optimal conditions bonus
            }
            adjustHealth(changeHealth);
        }
    }

    // Getters for state tracking
    public synchronized float getHealth() {
        return health;
    }

    public synchronized Set<String> getCurrentPests() {
        return currentPests;
    }

    public String getName() {
        return name;
    }

    public int getWaterRequirement() {
        return waterRequirement;
    }

    public Set<String> getParasites() {
        return parasites;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public synchronized String toString() {
        return String.format("%s - Health: %.2f%%, Active Pests: %s", name, health, currentPests);
    }

    public synchronized int getCurrentWaterLevel(){
        return this.currentWaterLevel;
    }

    public boolean isAlive(){
        return health>0 && health<=100;
    }
}
