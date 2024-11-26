package com.example.gardensimulation.Plant;

import com.example.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Rose extends Flowers {
    public Rose(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, ArrayList<Pest> parasites, boolean isAlive, int row, int col) {
        super(name, age, water_requirement, maxtemp_level, mintemp_level, fertilizer_level, water_level, time_for_watering, last_watering_time, days_for_fertilizer, last_fertilizer_day, parasites, true, row, col);
    }

   protected Image getPlantImage() {
        // Load the rose image (make sure the path is correct)
        return new Image("https://cdn.sanity.io/images/pn4rwssl/production/349d734442fdbcc734bd8060f126330fdf19e825-500x750.jpg?w=2880&q=75&auto=format"); // Replace with actual path
    }
}
