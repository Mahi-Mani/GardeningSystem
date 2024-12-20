package com.gardensimulation.Plant;

import com.gardensimulation.Pests.Pest;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Tomato extends Vegetables{
    public Tomato(String name, int age, int water_requirement, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day, ArrayList<Pest> parasites, boolean isAlive, int row, int col){
        super(name,age,water_requirement,maxtemp_level,mintemp_level,fertilizer_level,water_level, time_for_watering,last_watering_time,days_for_fertilizer, last_fertilizer_day, parasites,true, row, col);
    }

    public Tomato(ArrayList<Pest> currentPests) {
        super(currentPests);
    }

    protected Image getPlantImage() {
        // Load the tomato image
        return new Image("https://www.bhg.com/thmb/q8rfs1JikMcnBN8StgjLdYFwHdE=/4000x0/filters:no_upscale():strip_icc()/BHG-Cherry-Tomato-Plant-1407386653-69dc42c014cb4fc1a9788e595cf000b7.jpg"); // Replace with actual path
    }

    protected Image getAttackedImage() {
        return new Image("https://croach.com/wp-content/uploads/2015/11/Garden-Tomato-Pests-How-to-Identify-Prevent-Eliminate-Croach-1000x500.jpg"); // Pest-attacked image
    }
}
