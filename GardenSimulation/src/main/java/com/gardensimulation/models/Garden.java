package com.gardensimulation.models;

import java.util.HashMap;
import java.util.Map;

public class Garden {
    private final int gridRowsSize;
    private final int gridColumnSize;
    private Map<Integer, Plant> plants;


    public Garden(int rows, int cols) {
        gridColumnSize = cols;
        gridRowsSize = rows;
        plants = new HashMap<>();
    }

    public Map<Integer, Plant> getPlants() {
        return this.plants;
    }

    public void addPlant(Plant plant, int position) {
        if(position/10 > gridRowsSize || position%10 > gridColumnSize) {
            throw new RuntimeException("Invalid postion for the plant");
        }
        plants.put(position, plant);
    }

    public void setCurrentTemperature(float newTemperature) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCurrentTemperature'");
    }
}
