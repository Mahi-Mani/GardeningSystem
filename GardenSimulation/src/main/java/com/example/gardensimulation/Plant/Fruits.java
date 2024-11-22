package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Fruits extends Plants {
    ImageView plantView;

    public Fruits(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, boolean isAlive) {
        super(name, age, water_requirement, maxtemp_level, mintemp_level, fertilizer_level, water_level, time_for_watering, last_watering_time, days_for_fertilizer, last_fertilizer_day, true);
        plantView = new ImageView();
        plantView.setFitWidth(80);
        plantView.setFitHeight(80);
        plantView.setImage(getPlantImage());
    }

    // Abstract method to get the specific plant image
    protected abstract Image getPlantImage();

    //    Getter for imageView
    public ImageView getPlantView() {
        return plantView;
    }
}
