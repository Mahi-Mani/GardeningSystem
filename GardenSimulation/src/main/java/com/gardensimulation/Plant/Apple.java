package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Apple extends Fruits {
    public Apple(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, ArrayList<Pest> parasites, boolean isAlive, int row, int col) {
        super(name, age, water_requirement, maxtemp_level, mintemp_level, fertilizer_level, water_level, time_for_watering, last_watering_time, days_for_fertilizer, last_fertilizer_day, parasites,true, row, col);
    }

    public Apple(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the apple image
        return new Image("https://www.isons.com/wp-content/uploads/2023/07/Crimson-Crisp_shutterstock_323667563.jpg"); // Replace with actual path
    }

    protected Image getAttackedImage() {
        return new Image("https://www.bhg.com/thmb/Bb_85P595R4tWPNymZw9x_tbi4U=/1983x0/filters:no_upscale():strip_icc()/apple-scab-1142266260-ce7a480d595d445dac54eaf446a15b14.jpg"); // Pest-attacked image
    }
}
