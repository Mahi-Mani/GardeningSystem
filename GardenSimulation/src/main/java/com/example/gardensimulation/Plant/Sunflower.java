package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;

public class Sunflower extends Flowers {
    public Sunflower(String name, int age, int maxtemp_level, int mintemp_level, int fertilizer_level, int water_level, int time_for_watering, int last_watering_time, int days_for_fertilizer, int last_fertilizer_day) {
        super(name, age, maxtemp_level, mintemp_level, fertilizer_level, water_level, time_for_watering, last_watering_time, days_for_fertilizer, last_fertilizer_day);
    }

    protected Image getPlantImage() {
        // Load the sunflower image (make sure the path is correct)
        return new Image("https://media.istockphoto.com/id/927047528/vector/sunflower-flower-isolated.jpg?s=2048x2048&w=is&k=20&c=ARVqqtW_PFKOrVmLYpzR24RByFaAfpcflTeb0IKj6aM="); // Replace with actual path
    }
}
