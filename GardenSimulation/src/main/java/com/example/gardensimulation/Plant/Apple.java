package com.example.gardensimulation.Plant;

import javafx.scene.image.Image;

public class Apple extends Vegetables{
    public Apple(String name,int age, int maxtemp_level,int mintemp_level,int fertilizer_level, int water_level, int time_for_watering,int last_watering_time,int days_for_fertilizer, int last_fertilizer_day){
        super(name,age,maxtemp_level,mintemp_level,fertilizer_level,water_level, time_for_watering,last_watering_time,days_for_fertilizer, last_fertilizer_day);
    }

    protected Image getPlantImage() {
        // Load the apple image
        return new Image("https://thumbs.dreamstime.com/b/red-apple-isolated-clipping-path-19130134.jpg"); // Replace with actual path
    }
}
