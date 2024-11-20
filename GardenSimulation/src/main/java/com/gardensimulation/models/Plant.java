package com.gardensimulation.models;

import java.util.HashSet;
import java.util.Set;

public class Plant {
    private String name;
    private int waterRequirement;
    private Set<Parasites> parasites; // List of pests the plant is vulnerable to
    private Set<Parasites> currentPests; // Active pests currently affecting the plant
    private float health;
    private int currentWaterLevel; // Tracks how much water the plant has

    public Plant(String name, int waterRequirement, Set<Parasites> parasites) {
        this.name = name;
        this.waterRequirement = waterRequirement;
        this.parasites = parasites;
        this.currentPests = new HashSet<Parasites>();
        this.health = 100; // Default to fully healthy
        this.currentWaterLevel = 0;
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
    }

    /**
     * Handles pest infestation and adjusts health.
     */
    public synchronized void handlePestAttack(Parasites pest) {
        if (parasites.contains(pest) && !currentPests.contains(pest)) {
            currentPests.add (pest);
            adjustHealth(-15); // Reduces health due to pest
        }
    }

    /**
     * Removes a pest during pest control and adjusts health.
     */
    public synchronized void removePest(Parasites pest) {
        if (currentPests.remove(pest)) {
            adjustHealth(+10); // Restores health upon pest removal
        }
    }

    public synchronized void adjustHealthForTemperature(float temperature) {
        if (getHealth() != 0) {
            int changeHealth = 0;
            if (temperature < 50 || temperature > 95) {
                changeHealth =  -10; // Severe penalty
            } else if (temperature < 60 || temperature > 85) {
                changeHealth =  -5; // Mild penalty
            } else {
                changeHealth =  2; // Optimal conditions bonus
            }
            adjustHealth(changeHealth);
        }
    }

    // Getters for state tracking
    public synchronized float getHealth() {
        return health;
    }

    public synchronized Set<Parasites> getCurrentPests() {
        return currentPests;
    }

    public String getName() {
        return name;
    }

    public int getWaterRequirement() {
        return waterRequirement;
    }

    public Set<Parasites> getParasites() {
        return parasites;
    }

    @Override
    public synchronized String toString() {
        return String.format("%s - Health: %.2f%%, Active Pests: %s", name, health, currentPests);
    }

    public synchronized int getCurrentWaterLevel(){
        return this.currentWaterLevel;
    }
}
