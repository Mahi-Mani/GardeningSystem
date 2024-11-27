package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Tulip extends Flowers{
    public Tulip(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, ArrayList<Pest> parasites, boolean isAlive, int row, int col) {
        super(name, age, water_requirement, maxtemp_level, mintemp_level, fertilizer_level, water_level, time_for_watering, last_watering_time, days_for_fertilizer, last_fertilizer_day, parasites,true, row, col);
    }

    public Tulip(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the sunflower image (make sure the path is correct)
        return new Image("https://media.istockphoto.com/id/512048658/photo/flowers.jpg?s=612x612&w=0&k=20&c=iiZ5x9FVyGUx3GG88PptXl-HGFrJZPpagE2CxCOTimw="); // Replace with actual path
    }
}
