package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Sunflower extends Flowers {
    public Sunflower(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int water_level, ArrayList<Pest> parasites, boolean isAlive, int row, int col) {
        super(name, age, water_requirement, maxtemp_level, mintemp_level, water_level, parasites,isAlive, row, col);
    }

    public Sunflower(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the sunflower image
        return new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6keL_w2rCEYB7ieb9nRvXYgl2aOmxav3cHw&s"); // Replace with actual path
    }

    protected Image getAttackedImage() {
        return new Image("https://cdn.mos.cms.futurecdn.net/qpBhHREn6mUZuub8hfxP7h-415-80.jpg"); // Pest-attacked image
    }
}
