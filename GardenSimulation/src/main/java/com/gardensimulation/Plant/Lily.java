package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Lily extends Flowers {
    public Lily(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, ArrayList<Pest> parasites, boolean isAlive, int row, int col) {
        super(name, age, water_requirement, maxtemp_level, mintemp_level, fertilizer_level, water_level, time_for_watering, last_watering_time, days_for_fertilizer, last_fertilizer_day, parasites, true, row, col);
    }

    public Lily(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the tomato image
        return new Image("https://cdn.britannica.com/77/120977-050-41EE9568/Easter-lily.jpg"); // Replace with actual path
    }

    protected Image getAttackedImage() {
        return new Image("https://www.thompson-morgan.com/static-images/master/static-images/diseases/lily-disease/aphids-on-lily-flowers.jpg"); // Pest-attacked image
    }
}
