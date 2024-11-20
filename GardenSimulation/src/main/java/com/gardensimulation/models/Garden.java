package com.gardensimulation.models;

import java.util.HashMap;
import java.util.Map;

public class Garden {
    private final int gridRowsSize;
    private final int gridColumnSize;
    private Map<Integer, Plant> plants;

    Garden() {
        gridColumnSize = 10;
        gridRowsSize = 10;
        plants = new HashMap<>();
    }

    public void addPlant(Plant plant, int position) {
        if(position/10 < gridRowsSize && position%10 < gridColumnSize) {
            plants.put(position, plant);
        }
        throw new RuntimeException("Invalid postion for the plant");
    }

    public Map<Integer, Plant> getPlants() {
        return this.plants;
    }
}
